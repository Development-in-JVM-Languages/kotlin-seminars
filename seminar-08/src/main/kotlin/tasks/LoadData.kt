package org.edu.jvm.languages.tasks

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun connectToServer() {
    println("Connecting to server...")
    delay(1000) // Simulate network delay
    println("Connection established ✅")
}

suspend fun loadData() {
    println("Loading data...")
    delay(1000) // Simulate loading time
    println("Data loaded successfully 📦")
}

fun main() {
    // Our job is to connect to the server and load some data from it.
}
