package org.edu.jvm.languages

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

// fun main() {
//     val executor = Executors.newSingleThreadExecutor()
//     executor.submit {
//         println(2 / 0)
//     }
// }

fun findSum(num: Int): Int {
    val executor = Executors.newSingleThreadExecutor()
    var sum = 0

    for (i in 1..5) {
        executor.submit { sum += num / i }
    }

    executor.shutdown()
    executor.awaitTermination(60, TimeUnit.MILLISECONDS)

    return sum
}

fun main() {
    println(findSum(10))
}
