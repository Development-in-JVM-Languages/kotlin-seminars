package org.edu.jvm.languages.services

import kotlinx.coroutines.runBlocking
import org.edu.jvm.languages.api.OpenWeatherApiClient
import org.edu.jvm.languages.models.City

/**
 * Service for initializing city coordinates using the OpenWeather API
 */
class CityCoordinatesService(private val apiClient: OpenWeatherApiClient = OpenWeatherApiClient()) {

    fun initializeAllCityCoordinates() {
        runBlocking {
            City.entries.forEach { city ->
                try {
                    city.coordinates = apiClient.getCityCoordinates(city.cityName)
                    println("Initialized coordinates for ${city.name}: ${city.coordinates}")
                } catch (e: Exception) {
                    System.err.println("Failed to initialize coordinates for ${city.name}: ${e.message}")
                }
            }
        }
    }

    fun close() {
        apiClient.close()
    }
}
