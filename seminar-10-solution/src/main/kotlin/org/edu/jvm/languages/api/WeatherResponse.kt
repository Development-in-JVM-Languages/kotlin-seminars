package org.edu.jvm.languages.api

import kotlinx.serialization.Serializable
import org.edu.jvm.languages.models.MainWeatherData

/**
 * Response from the OpenWeather Current Weather API.
 */
@Serializable
data class WeatherResponse(
    val weather: List<Weather>,
    val main: MainWeatherData,
)
