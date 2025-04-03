package org.edu.jvm.languages.tasks

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main()  {
    // computeSum()
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
