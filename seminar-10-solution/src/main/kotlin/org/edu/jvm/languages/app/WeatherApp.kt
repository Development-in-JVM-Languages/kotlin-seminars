package org.edu.jvm.languages.app

import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.edu.jvm.languages.models.City
import org.edu.jvm.languages.models.City.Companion.getCurrentTimeString
import java.io.Closeable
import java.io.File

/**
 * Web server application for displaying weather information.
 * 
 * This class provides a web interface and API endpoints for accessing
 * city weather data and time information.
 */
class WeatherApp : Closeable {

    /**
     * Server instance that will be initialized when startServer is called.
     */
    private var server: io.ktor.server.engine.ApplicationEngine? = null

    /**
     * Serializes all cities to JSON.
     * 
     * @return JSON string containing all cities with their data
     */
    fun getCitiesAsJson(): String {
        val cities = City.entries.toList()
        return prettyJson.encodeToString(cities)
    }

    /**
     * Gets the current time for a city as a JSON string.
     * 
     * @param city The city to get time for
     * @return JSON string with the city's current time
     */
    fun getCityTimeAsJson(city: City): String {
        val cityTimeData = city.getCurrentTimeString()
        return prettyJson.encodeToString(cityTimeData)
    }

    /**
     * Loads the HTML template for the world map from resources.
     * 
     * @return HTML content as a string
     * @throws IllegalStateException if the HTML file is not found
     */
    fun getHtmlTemplate(): String {
        val classLoader = WeatherApp::class.java.classLoader
        val resource = classLoader.getResource("world_map.html")
            ?: throw IllegalStateException("HTML template file not found in resources")

        val file = File(resource.toURI())
        return file.readText()
    }

    /**
     * Starts the web server on the specified port.
     * 
     * @param port The port to listen on (default: 8080)
     */
    fun startServer(port: Int = 8080) {
        server = embeddedServer(Netty, port = port) {
            install(ContentNegotiation) {
                json(prettyJson)
            }

            routing {
                // Main page with the world map
                get("/") {
                    call.respondText(getHtmlTemplate(), ContentType.Text.Html)
                }

                // API endpoint for getting city time
                get("/api/time/{cityName}") {
                    val cityName = call.parameters["cityName"] ?: return@get call.respondText(
                        "Missing city name",
                        status = HttpStatusCode.BadRequest
                    )

                    try {
                        // Convert to uppercase to match the enum constant format
                        val normalizedCityName = cityName.uppercase().replace(" ", "_")
                        val city = City.valueOf(normalizedCityName)
                        val cityTimeJson = getCityTimeAsJson(city)
                        call.respond(cityTimeJson)
                    } catch (_: IllegalArgumentException) {
                        call.respondText(
                            "Invalid city name: $cityName. Available cities: ${City.entries.joinToString { it.name }}",
                            status = HttpStatusCode.BadRequest
                        )
                    }
                }

                // API endpoint for getting all cities data
                get("/api/cities") {
                    val citiesJson = getCitiesAsJson()
                    call.respond(citiesJson)
                }
            }
        }.start(wait = true)
    }

    /**
     * Stops the server when the application is shutting down.
     */
    override fun close() {
        server?.stop(1000, 2000)
    }

    companion object {
        /**
         * JSON serializer configured for pretty printing.
         */
        val prettyJson: Json = Json {
            prettyPrint = true
            encodeDefaults = true
        }
    }
}
