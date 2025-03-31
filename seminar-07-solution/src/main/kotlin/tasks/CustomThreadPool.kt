package org.edu.jvm.languages.tasks

import java.util.concurrent.Executor
import java.util.concurrent.LinkedBlockingQueue
import kotlin.random.Random

class CustomThreadPool(private val numberOfThreads: Int) : Executor {
    private val taskQueue: LinkedBlockingQueue<Runnable> = LinkedBlockingQueue()
    private val workers: List<Worker> = List(numberOfThreads) { Worker("Worker-$it") }
    private var isShutdown = false

    init {
        workers.forEach { it.start() }
    }

    override fun execute(command: Runnable) {
        if (!isShutdown) {
            taskQueue.put(command)
        }
    }

    fun shutdown() {
        isShutdown = true
        workers.forEach { it.interrupt() }
    }

    private inner class Worker(name: String) : Thread(name) {
        private fun exiting() {
            println("[$name] is exiting")
        }

        override fun run() {
            try {
                while (!isShutdown || taskQueue.isNotEmpty()) {
                    val task = taskQueue.poll()
                    task?.run()
                }
            } catch (e: InterruptedException) {
                // Worker interrupted, exiting
            } finally {
                exiting()
            }
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
