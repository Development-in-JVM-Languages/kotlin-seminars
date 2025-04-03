package org.edu.jvm.languages.examples

import kotlinx.coroutines.*

suspend fun taskThatMightFail(index: Int): String {
    delay(100 * index.toLong())
    if (index == 2) {
        throw Exception("Failed at task $index")
    }
    return "Result of task $index"
}

//fun main() = runBlocking {
//    val deferredTasks = (1..3).map { index ->
//        async { taskThatMightFail(index) }
//    }
//
//    try {
//        val results = deferredTasks.awaitAll()
//        println("Results: $results")
//    } catch (e: Exception) {
//        println("Caught an exception: ${e.message}")
//    } finally {
//        // Cancel all remaining tasks if needed (optional)
//        deferredTasks.forEach { it.cancel() }
//    }
//}

fun main() {
    val num = listOf(1, 2, 3, 4, 5)
    val result = num
        .asSequence()
        .map { println("Map: $it"); it * 2 }
        .filter { println("Filter: $it"); it > 5 }
        .first()
    println("Result: $result")
}