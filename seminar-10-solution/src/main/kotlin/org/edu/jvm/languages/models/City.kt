package org.edu.jvm.languages.models

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.*
import org.edu.jvm.languages.api.WeatherResponse
import org.edu.jvm.languages.serializers.CitySerializer

/**
 * Represents a city with its geographical and weather information.
 * 
 * This enum class contains predefined cities with their names and timezones.
 * It also stores mutable properties for coordinates and weather data that
 * can be updated through the provided methods.
 */
@Serializable(with = CitySerializer::class)
enum class City(
    val cityName: String,
    val timezone: String,
) {
    BOSTON("Boston", "America/New_York"),
    NEW_YORK("New York", "America/New_York"),
    WARSAW("Warsaw", "Europe/Warsaw"),
    BREMEN("Bremen", "Europe/Berlin"),
    MUNICH("Munich", "Europe/Berlin"),
    BERLIN("Berlin", "Europe/Berlin"),
    AMSTERDAM("Amsterdam", "Europe/Amsterdam"),
    BELGRADE("Belgrade", "Europe/Belgrade"),
    YEREVAN("Yerevan", "Asia/Yerevan"),
    PRAGUE("Prague", "Europe/Prague"),
    PAPHOS("Paphos", "Europe/Nicosia"),
    SAN_FRANCISCO("San Francisco", "America/Los_Angeles"),
    SHANGHAI("Shanghai", "Asia/Shanghai");

    // Coordinates properties
    var latitude: Double = 0.0
        private set

    var longitude: Double = 0.0
        private set

    // Weather data properties
    var temperature: Double? = null
        private set

    var weatherDescription: String? = null
        private set

    /**
     * Updates the coordinates for this city.
     * 
     * @param lat Latitude coordinate
     * @param lon Longitude coordinate
     */
    fun updateCoordinates(lat: Double, lon: Double) {
        latitude = lat
        longitude = lon
    }

    /**
     * Updates the weather data for this city.
     * 
     * @param weatherData Weather data response from the API
     */
    fun updateWeatherData(weatherData: WeatherResponse) {
        temperature = weatherData.main.temp
        weatherDescription = weatherData.weather.firstOrNull()?.description
    }

    /**
     * Checks if the city has valid coordinates.
     * 
     * @return true if both latitude and longitude are non-zero
     */
    fun hasValidCoordinates(): Boolean = latitude != 0.0 || longitude != 0.0

    companion object {
        /**
         * Extension function to get the current time string for a city.
         *
         * @return A formatted string with the current time in the city's timezone
         */
        fun City.getCurrentTimeString(): String {
            val now = Clock.System.now()
            val timeZone = TimeZone.of(timezone)
            val localDateTime = now.toLocalDateTime(timeZone)
            return "Time in $cityName is ${localDateTime.hour}:${String.format("%02d", localDateTime.minute)}:${String.format("%02d", localDateTime.second)}"
        }

    }

}
