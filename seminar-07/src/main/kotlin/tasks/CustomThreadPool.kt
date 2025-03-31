package org.edu.jvm.languages.tasks

import java.util.concurrent.Executor
import kotlin.random.Random

class CustomThreadPool(private val numberOfThreads: Int) : Executor {
    override fun execute(command: Runnable) {
        TODO("Not yet implemented")
    }

    fun shutdown() {
        TODO("Not yet implemented")
    }

    private inner class Worker(name: String) : Thread(name) {
        private fun exiting() {
            println("[$name] is exiting")
        }

        override fun run() {
            TODO("Not yet implemented")
        }
    }
}

fun residentSleeper(secs: Int) {
    println("${Thread.currentThread().name} is going to sleep for $secs seconds.")
    Thread.sleep(secs * 1000L)
    println("${Thread.currentThread().name} is awake!")
}

fun main() {
    val nThreads = 4
    val simpleThreadPool = CustomThreadPool(nThreads)
    repeat(10) {
        simpleThreadPool.execute {
            residentSleeper(Random.nextInt(1, 5))
        }
    }
    simpleThreadPool.shutdown()
    println("done")
}
