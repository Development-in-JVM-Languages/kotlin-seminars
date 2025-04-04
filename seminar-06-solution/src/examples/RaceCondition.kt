package examples

var counter = 0

fun main() {
    val thread1 = Thread {
        for (i in 1..100000) counter++
    }
    val thread2 = Thread {
        for (i in 1..100000) counter++
    }

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()

    println("Final counter value: $counter")  // Should be 200000 but likely won't be
}
