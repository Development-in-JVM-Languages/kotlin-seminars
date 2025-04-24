package org.edu.jvm.languages.models

data class Coordinates(
    val latitude: Double,
    val longitude: Double
) {
    companion object {
        val EMPTY = Coordinates(0.0, 0.0)
    }
}