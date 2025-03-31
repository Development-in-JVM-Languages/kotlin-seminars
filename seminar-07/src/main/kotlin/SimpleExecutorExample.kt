package org.edu.jvm.languages

import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

fun main() {
    val totalTasks = 10
    val executor = Executors.newFixedThreadPool(4)

    val latch = CountDownLatch(totalTasks)

    for (i in 1..totalTasks) {
        executor.submit {

            try {
                println("Task $i started on ${Thread.currentThread().name}")
                val data = downloadSimulated(i)
                val processed = processSimulated(data)
                println("Task $i processed: $processed")
            } catch (e: Exception) {
                println("Task $i failed: ${e.message}")
            } finally {
                latch.countDown()
            }
        }
    }

    println("✅ Waiting for tasks to finish...")
    latch.await() // Wait for all tasks to complete
    executor.shutdown()
    println("🎉 All tasks completed.")
}
