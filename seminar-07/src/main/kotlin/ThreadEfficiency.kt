package org.edu.jvm.languages

import kotlin.system.measureTimeMillis

fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) return false
    }
    return true
}

fun cpuTask(start: Int, end: Int): Int {
    var count = 0
    for (i in start..end) {
        if (isPrime(i)) count++
    }
    return count
}

fun runWithThreads(threadCount: Int, range: IntRange) {
    val step = range.count() / threadCount
    val threads = mutableListOf<Thread>()
    var totalPrimes = 0

    val time = measureTimeMillis {
        val results = MutableList(threadCount) { 0 }

        for (i in 0 until threadCount) {
            val start = range.first + i * step
            val end = if (i == threadCount - 1) range.last else start + step - 1

            val t = Thread {
                results[i] = cpuTask(start, end)
            }
            threads += t
            t.start()
        }

        threads.forEach { it.join() }
        totalPrimes = results.sum()
    }

    println("Threads: $threadCount | Time: ${time}ms | Total Primes: $totalPrimes")
}

fun main() {
    val range = 1..500_000
    listOf(1, 2, 5, 10, 20, 100, 1_000, 10_000).forEach {
        runWithThreads(it, range)
    }
}
