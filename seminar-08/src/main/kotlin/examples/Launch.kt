package org.edu.jvm.languages.examples

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread


suspend fun mySuspoendFun() {
    println("Hello from mySuspendFun!")
}

fun main() {
    GlobalScope.launch {
        delay(1000L)
        println("World 1!")
    }
    GlobalScope.launch {
        delay(1000L)
        println("World 2!")
    }
    GlobalScope.launch {
        delay(1000L)
        println("World 3!")
    }
    println("Hello,")
    // Thread.sleep(2000L)
}

// fun main() {
//     thread(isDaemon = true) {
//         Thread.sleep(1000L)
//         println("World 1!")
//     }
//     thread(isDaemon = true) {
//         Thread.sleep(1000L)
//         println("World 2!")
//     }
//     thread(isDaemon = true) {
//         Thread.sleep(1000L)
//         println("World 3!")
//     }
//     println("Hello,")
//     // Thread.sleep(2000L)
// }
