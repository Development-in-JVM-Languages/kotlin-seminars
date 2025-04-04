package org.edu.jvm.languages.tasks

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main()  {
    computeSum()
}

fun computeSum() = runBlocking {
    val time = measureTimeMillis {
        val one = async { longRunningComputation1() }
        val two = async { longRunningComputation2() }

        val sum = one.await() + two.await()
        println("The answer is $sum")
    }
    println("Completed in $time ms")
}

// These simulate long-running work
suspend fun longRunningComputation1(): Int = withContext(Dispatchers.Default) {
    delay(1000)
    42
}

suspend fun longRunningComputation2(): Int = withContext(Dispatchers.Default) {
    delay(1000)
    58
}
