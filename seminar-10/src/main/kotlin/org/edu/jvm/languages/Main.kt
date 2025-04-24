package org.edu.jvm.languages

import org.edu.jvm.languages.app.WeatherApp
import org.edu.jvm.languages.services.CityCoordinatesService
import org.edu.jvm.languages.services.CityWeatherService


fun main() {
    val app = WeatherApp()

    println("Initializing city coordinates from OpenWeather API...")
    val coordinatesService = CityCoordinatesService()
    coordinatesService.initializeAllCityCoordinates()

    println("Fetching weather data from OpenWeather API...")
    val weatherService = CityWeatherService()
    weatherService.updateAllCityWeatherInfo()

    println("Starting server on http://localhost:8080")
    println("API endpoint for city time: http://localhost:8080/api/time/{cityName}")
    println("API endpoint for city data: http://localhost:8080/api/cities")

    app.startServer()

    Runtime.getRuntime().addShutdownHook(Thread {
        coordinatesService.close()
        weatherService.close()
    })
}
