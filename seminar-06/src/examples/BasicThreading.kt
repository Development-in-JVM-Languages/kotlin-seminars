package examples

fun main() {
    for (i in 1..5) {
        Thread {
            for (j in 1..5) {
                println("Thread $i - Number: $j")
                Thread.sleep(500)  // Delay for clarity
            }
        }.start()
    }
}
