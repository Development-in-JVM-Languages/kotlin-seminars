package org.edu.jvm.languages

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val threads = mutableListOf<Thread>()

    try {
        for (i in 1..100_000) {
            val t = Thread {
                println("Thread $i started")
                Thread.sleep(10_000) // keep thread alive
            }
            threads += t
            t.start()
        }
    } catch (e: OutOfMemoryError) {
        println("💣 Too many threads! System ran out of memory.")
    }
}
