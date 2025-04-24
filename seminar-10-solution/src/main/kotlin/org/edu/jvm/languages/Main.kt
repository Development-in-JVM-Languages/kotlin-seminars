package org.edu.jvm.languages

import org.edu.jvm.languages.app.WeatherApp
import org.edu.jvm.languages.services.CityCoordinatesService
import org.edu.jvm.languages.services.CityWeatherService
import kotlin.concurrent.thread

/**
 * Weather Map Application
 * 
 * This application fetches weather data for various cities around the world
 * and displays it on an interactive map through a web interface.
 * 
 * The application flow:
 * 1. Initialize city coordinates from the OpenWeather API
 * 2. Fetch current weather data for all cities
 * 3. Start a web server to display the data
 * 
 * The application requires an OpenWeather API key to be set as an
 * environment variable named WEATHER_API_KEY.
 */
fun main() {
    // Create services with resource management
    val coordinatesService = CityCoordinatesService()
    val weatherService = CityWeatherService()
    var app: WeatherApp? = null

    try {
        // Step 1: Initialize city coordinates
        println("Initializing city coordinates from OpenWeather API...")
        coordinatesService.initializeAllCityCoordinates()

        // Step 2: Fetch weather data
        println("Fetching weather data from OpenWeather API...")
        weatherService.updateAllCityWeatherInfo()

        // Step 3: Start the web server
        println("Starting server on http://localhost:8080")
        println("API endpoint for city time: http://localhost:8080/api/time/{cityName}")
        println("API endpoint for city data: http://localhost:8080/api/cities")

        app = WeatherApp()
        app.startServer()

        // Register shutdown hook to properly close all resources
        Runtime.getRuntime().addShutdownHook(thread {
            println("Shutting down application...")
            app.close()
            coordinatesService.close()
            weatherService.close()
        })
    } catch (e: Exception) {
        println("Error starting application: ${e.message}")
        // Ensure resources are closed even if startup fails
        app?.close()
        coordinatesService.close()
        weatherService.close()
    }
}
