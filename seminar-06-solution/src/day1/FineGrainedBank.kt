@file:Suppress("DuplicatedCode")

package day1

import day1.Bank.Companion.MAX_AMOUNT
import java.util.concurrent.locks.*

class FineGrainedBank(accountsNumber: Int) : Bank {
    private val accounts: Array<Account> = Array(accountsNumber) { Account() }

    override fun getAmount(id: Int): Long {
        val account = accounts[id]
        account.lock.lock()
        try {
            return account.amount
        } finally {
            account.lock.unlock()
        }
    }

    override fun deposit(id: Int, amount: Long): Long {
        val account = accounts[id]
        account.lock.lock()
        try {
            require(amount > 0) { "Invalid amount: $amount" }
            check(!(amount > MAX_AMOUNT || account.amount + amount > MAX_AMOUNT)) { "Overflow" }
            account.amount += amount
            return account.amount
        } finally {
            account.lock.unlock()
        }
    }

    override fun withdraw(id: Int, amount: Long): Long {
        val account = accounts[id]
        account.lock.lock()
        try {
            require(amount > 0) { "Invalid amount: $amount" }
            check(account.amount - amount >= 0) { "Underflow" }
            account.amount -= amount
            return account.amount
        } finally {
            account.lock.unlock()
        }
    }

    override fun transfer(fromId: Int, toId: Int, amount: Long) {
        if (fromId == toId) return

        val firstLock = if (fromId < toId) accounts[fromId].lock else accounts[toId].lock
        val secondLock = if (fromId < toId) accounts[toId].lock else accounts[fromId].lock

        firstLock.lock()
        secondLock.lock()
        try {
            val from = accounts[fromId]
            val to = accounts[toId]
            require(amount > 0) { "Invalid amount: $amount" }
            check(amount <= from.amount) { "Underflow" }
            check(!(amount > MAX_AMOUNT || to.amount + amount > MAX_AMOUNT)) { "Overflow" }
            from.amount -= amount
            to.amount += amount
        } finally {
            secondLock.unlock()
            firstLock.unlock()
        }
    }

    /**
     * Private account data structure.
     */
    class Account {
        /**
         * Amount of funds in this account.
         */
        var amount: Long = 0

        val lock = ReentrantLock()
    }
}
