package org.edu.jvm.languages

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class BoundedQueue<T>(private val capacity: Int) {
    private val queue = mutableListOf<T>()
    private val lock = ReentrantLock()
    private val notFull = lock.newCondition()
    private val notEmpty = lock.newCondition()

    fun produce(item: T) {
        lock.withLock {
            while (queue.size == capacity) {
                println("Queue is full. Producer is waiting...")
                notFull.await()
            }

            queue.add(item)
            println("Produced: $item")
            notEmpty.signal() // Notify a waiting consumer
        }
    }

    fun consume(): T {
        lock.withLock {
            while (queue.isEmpty()) {
                println("Queue is empty. Consumer is waiting...")
                notEmpty.await()
            }

            val item = queue.removeAt(0)
            println("Consumed: $item")
            notFull.signal() // Notify a waiting producer
            return item
        }
    }
}

fun main() {
    val queue = BoundedQueue<Int>(2)

    val producer = Thread {
        for (i in 1..5) {
            queue.produce(i)
            Thread.sleep(500)
        }
    }

    val consumer = Thread {
        repeat(5) {
            Thread.sleep(1000)
            queue.consume()
        }
    }

    producer.start()
    consumer.start()

    producer.join()
    consumer.join()
    println("All done!")
}
