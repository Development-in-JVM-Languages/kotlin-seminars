package org.edu.jvm.languages.models

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.*
import org.edu.jvm.languages.api.OpenWeatherApiClient.WeatherResponse
import org.edu.jvm.languages.serializers.CitySerializer

@Serializable(with = CitySerializer::class)
enum class City(
    val cityName: String,
    val timezone: String,
) {
    Boston("Boston", "America/New_York"),
    NewYork("New York", "America/New_York"),
    Warsaw("Warsaw", "Europe/Warsaw"),
    Bremen("Bremen", "Europe/Berlin"),
    Munich("Munich", "Europe/Berlin"),
    Berlin("Berlin", "Europe/Berlin"),
    Amsterdam("Amsterdam", "Europe/Amsterdam"),
    Belgrade("Belgrade", "Europe/Belgrade"),
    Yerevan("Yerevan", "Asia/Yerevan"),
    Prague("Prague", "Europe/Prague"),
    Paphos("Paphos", "Europe/Nicosia"),
    SanFrancisco("San Francisco", "America/Los_Angeles"),
    Shanghai("Shanghai", "Asia/Shanghai");

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
     * Updates the coordinates for this city
     * @param lat Latitude
     * @param lon Longitude
     */
    fun updateCoordinates(lat: Double, lon: Double) {
        latitude = lat
        longitude = lon
        println("Updated coordinates for $name: lat=$latitude, lon=$longitude")
    }

    /**
     * Updates the weather data for this city
     * @param weatherData Weather data response
     * @return true if the update was successful, false otherwise
     */
    fun updateWeatherData(weatherData: WeatherResponse) {
        temperature = weatherData.main.temp
        weatherDescription = weatherData.weather.firstOrNull()?.description
        println("Updated weather for $name: temp=${temperature}°C, description=${weatherDescription}")
    }

    companion object {
        fun City.getCurrentTimeString(): String {
            val now = Clock.System.now()
            val timeZone = TimeZone.of(timezone)
            val localDateTime = now.toLocalDateTime(timeZone)
            return "Time in $cityName is ${localDateTime.hour}:${localDateTime.minute}:${localDateTime.second}"
        }
    }
}
