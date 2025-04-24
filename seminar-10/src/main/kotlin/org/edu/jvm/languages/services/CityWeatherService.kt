package org.edu.jvm.languages.services

import kotlinx.coroutines.runBlocking
import org.edu.jvm.languages.api.OpenWeatherApiClient
import org.edu.jvm.languages.models.City
import org.edu.jvm.languages.models.Coordinates

/**
 * Service for fetching and updating weather information for cities
 */
class CityWeatherService(private val apiClient: OpenWeatherApiClient = OpenWeatherApiClient()) {

    /**
     * Fetches and updates weather information for all cities
     */
    fun updateAllCityWeatherInfo() {
        runBlocking {
            City.entries.forEach { city ->
                try {

                    if (city.coordinates == Coordinates.EMPTY) {
                        System.err.println("Skipping weather update for ${city.name}: No coordinates available")
                        return@forEach
                    }
                    
                    val weatherData = apiClient.getWeatherData(city.coordinates ?: Coordinates.EMPTY)
                    
                    // Update city with weather information
                    val weatherDescription = if (weatherData.weather.isNotEmpty()) {
                        weatherData.weather.first().description
                    } else {
                        null
                    }
                    
                    val cityClass = City::class.java
                    
                    val temperatureField = cityClass.getDeclaredField("temperature")
                    temperatureField.isAccessible = true
                    temperatureField.set(city, weatherData.main.temp)
                    temperatureField.isAccessible = false
                    
                    val weatherDescriptionField = cityClass.getDeclaredField("weatherDescription")
                    weatherDescriptionField.isAccessible = true
                    weatherDescriptionField.set(city, weatherDescription)
                    weatherDescriptionField.isAccessible = false
                    
                    println("Updated weather for ${city.name}: temp=${weatherData.main.temp}°C, description=$weatherDescription")
                } catch (e: Exception) {
                    System.err.println("Failed to update weather for ${city.name}: ${e.message}")
                }
            }
        }
    }

    /**
     * Closes the API client
     */
    fun close() {
        apiClient.close()
    }
}