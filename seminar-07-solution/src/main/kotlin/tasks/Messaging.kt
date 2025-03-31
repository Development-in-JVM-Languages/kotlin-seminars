package org.edu.jvm.languages.tasks

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


/* Do not change this class */
data class Message(val from: String, val to: String, val text: String)

/* Do not change this interface */
interface AsyncMessageSender {
    fun sendMessages(messages: List<Message>)
    fun stop()
}

class AsyncMessageSenderImpl(private val repeatFactor: Int) : AsyncMessageSender {
    private val executor: ExecutorService = Executors.newFixedThreadPool(4)

    override fun sendMessages(messages: List<Message>) {
        messages.forEach { message ->
            repeat(repeatFactor) {
                executor.execute { println("Sent: $message") }
            }
        }
    }

    override fun stop() {
        executor.shutdown()
        executor.awaitTermination(5, TimeUnit.SECONDS)
    }
}

fun notifyAboutEnd() {
    println("Completed.")
}

fun main() {
    val sender: AsyncMessageSender = AsyncMessageSenderImpl(3)

    val messages = listOf(
        Message("John", "Mary", "Hello!"),
        Message("Clara", "Bruce", "How are you today?")
    )

    sender.sendMessages(messages)
    sender.stop()

    notifyAboutEnd()
}
