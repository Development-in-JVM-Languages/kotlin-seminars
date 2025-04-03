package org.edu.jvm.languages.examples

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking





fun main() {
    runBlocking {
        delay(1000L)
        println("World 1!")
    }
    runBlocking {
        delay(1000L)
        println("World 2!")
    }
    runBlocking {
        delay(1000L)
        println("World 3!")
    }
    println("Hello,")
}



//fun main() {
//    Thread.sleep(1000L)
//    println("World!")
//    Thread.sleep(1000L)
//    println("World!")
//    Thread.sleep(1000L)
//    println("World!")
//    println("Hello,")
//}



//fun main() = runBlocking {
//    GlobalScope.launch {
//        delay(1000L)
//        println("World!")
//    }
//    GlobalScope.launch {
//        delay(1000L)
//        println("World!")
//    }
//    GlobalScope.launch {
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,")
//    delay(2000L) // still needed
//}