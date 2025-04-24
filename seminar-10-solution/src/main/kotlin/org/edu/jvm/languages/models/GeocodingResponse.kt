package org.edu.jvm.languages.models

import kotlinx.serialization.Serializable

/**
 * Response from the OpenWeather Geocoding API.
 */
@Serializable
data class GeocodingResponse(
    val lat: Double,
    val lon: Double,
)