package org.edu.jvm.languages

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class NamedTask(private val name: String) : Runnable {
    override fun run() {
        println("${Thread.currentThread().name} is running $name")
        Thread.sleep(2000)
    }

    override fun toString(): String {
        return name
    }
}

fun main() {
    val NUMBER_OF_TASKS = 10
    val POOL_SIZE = 4
    val threads_size = Runtime.getRuntime().availableProcessors()
    val executor: ExecutorService = Executors.newFixedThreadPool(POOL_SIZE)



    for (i in 0..NUMBER_OF_TASKS) {
        executor.submit {
            val taskName: String = "task-$i"
            val threadName: String = Thread.currentThread().name
            println("$threadName executes $taskName")
        }
    }
    val notExecutedTasks = executor.shutdownNow()
    notExecutedTasks.forEach {
        println(" - ${it}")
    }
    executor.shutdown()
//    val notExecutedTasks = executor.shutdownNow()
}
