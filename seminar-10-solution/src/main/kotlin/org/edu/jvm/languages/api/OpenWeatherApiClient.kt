package org.edu.jvm.languages.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.edu.jvm.languages.models.GeocodingResponse
import java.io.Closeable

/**
 * Client for the OpenWeather API to fetch city coordinates and weather data.
 * 
 * This client provides methods to interact with the OpenWeather API,
 * including geocoding (getting coordinates for cities) and current weather data.
 */
class OpenWeatherApiClient : Closeable {

    companion object {
        // API endpoints as constants for better maintainability
        private const val GEOCODING_API_URL = "https://api.openweathermap.org/geo/1.0/direct"
        private const val WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather"

        // Environment variable name for the API key
        private const val API_KEY_ENV_VAR = "WEATHER_API_KEY"
    }

    /**
     * HTTP client configured with JSON serialization support.
     */
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = false
            })
        }
    }

    /**
     * Gets the API key from environment variables.
     * 
     * @throws IllegalStateException if the API key is not set
     */
    private val apiKey: String
        get() = System.getenv(API_KEY_ENV_VAR) 
            ?: throw IllegalStateException("$API_KEY_ENV_VAR environment variable is not set. Please set it to your OpenWeather API key.")

    /**
     * Fetches the coordinates (latitude and longitude) for a given city.
     * 
     * @param cityName The name of the city
     * @param countryCode Optional country code (e.g., "US", "UK")
     * @param limit Optional limit for the number of results
     * @return A pair of latitude and longitude
     * @throws NoSuchElementException if no coordinates are found for the city
     * @throws Exception if there's an error communicating with the API
     */
    suspend fun getCityCoordinates(
        cityName: String, 
        countryCode: String? = null, 
        limit: Int = 1
    ): Pair<Double, Double> {
        try {
            val query = if (countryCode != null) "$cityName,$countryCode" else cityName

            val response: List<GeocodingResponse> = client.get(GEOCODING_API_URL) {
                parameter("q", query)
                parameter("limit", limit)
                parameter("appid", apiKey)
            }.body()

            if (response.isEmpty()) {
                throw NoSuchElementException("No coordinates found for city: $cityName")
            }

            val result = response.first()
            return Pair(result.lat, result.lon)
        } catch (e: Exception) {
            // Wrap the exception with more context
            throw Exception("Failed to get coordinates for $cityName: ${e.message}", e)
        }
    }

    /**
     * Fetches weather data for a given latitude and longitude.
     * 
     * @param lat Latitude coordinate
     * @param lon Longitude coordinate
     * @param units Units of measurement (standard, metric, imperial)
     * @return WeatherResponse object containing weather data
     * @throws Exception if there's an error communicating with the API
     */
    suspend fun getWeatherData(
        lat: Double, 
        lon: Double, 
        units: String = "metric"
    ): WeatherResponse {
        try {
            return client.get(WEATHER_API_URL) {
                parameter("lat", lat)
                parameter("lon", lon)
                parameter("appid", apiKey)
                parameter("units", units)
            }.body()
        } catch (e: Exception) {
            // Wrap the exception with more context
            throw Exception("Failed to get weather data for coordinates ($lat, $lon): ${e.message}", e)
        }
    }

    /**
     * Closes the HTTP client when the API client is no longer needed.
     */
    override fun close() {
        client.close()
    }
}
