package org.edu.jvm.languages.api

import kotlinx.serialization.Serializable

/**
 * Weather condition information.
 */
@Serializable
data class Weather(
    val description: String,
)
