package org.edu.jvm.languages.services

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.edu.jvm.languages.api.OpenWeatherApiClient
import org.edu.jvm.languages.models.City
import java.io.Closeable

/**
 * Service for initializing city coordinates using the OpenWeather API.
 * 
 * This service fetches geographical coordinates for all cities in the City enum
 * and updates their latitude and longitude properties.
 * 
 * @property apiClient The API client used to fetch coordinates data
 */
class CityCoordinatesService(private val apiClient: OpenWeatherApiClient = OpenWeatherApiClient()) : Closeable {

    /**
     * Initializes coordinates for all cities in parallel.
     * 
     * This method fetches coordinates for all cities concurrently using coroutines
     * and updates each city with its coordinates.
     */
    suspend fun initializeAllCityCoordinatesAsync() = coroutineScope {
        val deferredResults = City.entries.map { city ->
            async {
                try {
                    val (latitude, longitude) = apiClient.getCityCoordinates(city.cityName)
                    city.updateCoordinates(latitude, longitude)
                    println("Initialized coordinates for ${city.name}: lat=$latitude, lon=$longitude")
                } catch (e: Exception) {
                    System.err.println("Failed to initialize coordinates for ${city.name}: ${e.message}")
                }
            }
        }

        // Wait for all coordinate fetching operations to complete
        deferredResults.awaitAll()
    }

    /**
     * Initializes coordinates for all cities in a blocking manner.
     * 
     * This is a convenience method that calls the suspend function
     * in a blocking context. For production code, consider using the suspend
     * version directly from a coroutine context.
     */
    fun initializeAllCityCoordinates() = kotlinx.coroutines.runBlocking {
        withContext(Dispatchers.IO) {
            initializeAllCityCoordinatesAsync()
        }
    }

    /**
     * Closes the API client when the service is no longer needed.
     */
    override fun close() {
        apiClient.close()
    }
}
