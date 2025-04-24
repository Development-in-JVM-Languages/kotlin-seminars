package org.edu.jvm.languages.services

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.edu.jvm.languages.api.OpenWeatherApiClient
import org.edu.jvm.languages.models.City
import java.io.Closeable

/**
 * Service for fetching and updating weather information for cities.
 * 
 * This service fetches current weather data for all cities in the City enum
 * and updates their temperature and weather description properties.
 * 
 * @property apiClient The API client used to fetch weather data
 */
class CityWeatherService(private val apiClient: OpenWeatherApiClient = OpenWeatherApiClient()) : Closeable {

    /**
     * Fetches and updates weather information for all cities in parallel.
     * 
     * This method fetches weather data for all cities concurrently using coroutines
     * and updates each city with its weather information.
     */
    suspend fun updateAllCityWeatherInfoAsync() = coroutineScope {
        val deferredResults = City.entries
            .filter { it.hasValidCoordinates() }
            .map { city ->
                async {
                    try {
                        val weatherData = apiClient.getWeatherData(city.latitude, city.longitude)
                        city.updateWeatherData(weatherData)
                        println("Updated weather for ${city.name}: temp=${weatherData.main.temp}°C, description=${weatherData.weather.firstOrNull()?.description}")
                    } catch (e: Exception) {
                        System.err.println("Failed to update weather for ${city.name}: ${e.message}")
                    }
                }
            }

        // Wait for all weather fetching operations to complete
        deferredResults.awaitAll()
    }

    /**
     * Fetches and updates weather information for all cities in a blocking manner.
     * 
     * This is a convenience method that calls the suspend function
     * in a blocking context. For production code, consider using the suspend
     * version directly from a coroutine context.
     */
    fun updateAllCityWeatherInfo() = runBlocking {
        withContext(Dispatchers.IO) {
            updateAllCityWeatherInfoAsync()
        }
    }

    /**
     * Closes the API client when the service is no longer needed.
     */
    override fun close() {
        apiClient.close()
    }
}
