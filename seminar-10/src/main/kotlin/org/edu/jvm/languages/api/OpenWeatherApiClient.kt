package org.edu.jvm.languages.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import org.edu.jvm.languages.models.Coordinates

/**
 * Client for the OpenWeather API to fetch city coordinates and weather data
 */
class OpenWeatherApiClient {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(TODO())
        }
    }

    private val apiKey: String
        get() = System.getenv("WEATHER_API_KEY") ?: throw IllegalStateException("WEATHER_API_KEY environment variable is not set")

    suspend fun getCityCoordinates(cityName: String, countryCode: String? = null, limit: Int = 1): Coordinates = TODO()

    suspend fun getWeatherData(coordinates: Coordinates, units: String = "metric"): WeatherResponse = TODO()

    /**
     * Closes the HTTP client
     */
    fun close() {
        client.close()
    }
}
