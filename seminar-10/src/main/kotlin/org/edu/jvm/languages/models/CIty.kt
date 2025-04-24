package org.edu.jvm.languages.models

import kotlinx.serialization.Serializable
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
    Shanghai("Shanghai", "Asia/Shanghai");

    var coordinates: Coordinates? = null

    var temperature: Double? = null
        private set

    var weatherDescription: String? = null
        private set


    companion object {
        fun City.getCurrentTimeString(): String = TODO()
    }
}
