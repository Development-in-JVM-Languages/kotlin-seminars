package org.edu.jvm.languages.app

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.json.Json
import org.edu.jvm.languages.models.City

class WeatherApp {
    fun getCitiesAsJson(): String = TODO()

    fun getCityTimeAsJson(city: City): String = TODO()

    fun getHtmlTemplate(): String = TODO()

    fun startServer(port: Int = 8080) {
        embeddedServer(Netty, port = port) {
            TODO()
        }.start(wait = true)
    }

    companion object {
        val prettyJson: Json = TODO()
    }
}
