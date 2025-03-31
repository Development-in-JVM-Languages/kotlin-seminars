package org.edu.jvm.languages

import java.util.concurrent.atomic.AtomicInteger

fun downloadSimulated(i: Int): String {
    Thread.sleep(500L)
    return "data_$i"
}

fun processSimulated(data: String): String {
    Thread.sleep(300L)
    return data.uppercase()
}

fun main() {
    val totalTasks = 10
    val threads = mutableListOf<Thread>()
    val completed = AtomicInteger(0)
    val lock = Object()

    for (i in 1..totalTasks) {
        val thread = Thread {
            try {
                println("Thread $i starting")
                val data = downloadSimulated(i)
                val processed = processSimulated(data)

                synchronized(lock) {
                    println("Thread $i finished processing: $processed")
                }

                val done = completed.incrementAndGet()
                if (done == totalTasks) {
                    synchronized(lock) {
                        lock.notify()
                    }
                }

            } catch (e: Exception) {
                println("Thread $i failed: ${e.message}")
            }
        }

        threads += thread
        thread.start()
    }

    synchronized(lock) {
        while (completed.get() < totalTasks) {
            try {
                lock.wait()
            } catch (e: InterruptedException) {
                println("Main thread interrupted.")
            }
        }
    }

    println("✅ All tasks completed.")
}
