package org.edu.jvm.languages.models

import kotlinx.serialization.Serializable

/**
 * Main weather data including temperature.
 */
@Serializable
data class MainWeatherData(
    val temp: Double,
)
