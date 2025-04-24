package org.edu.jvm.languages.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*
import org.edu.jvm.languages.api.Weather
import org.edu.jvm.languages.api.WeatherResponse
import org.edu.jvm.languages.models.City
import org.edu.jvm.languages.models.MainWeatherData

/**
 * Custom serializer for the City enum class.
 * 
 * This serializer handles the serialization and deserialization of City objects,
 * including their mutable properties like coordinates and weather data.
 */
object CitySerializer : KSerializer<City> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("City") {
        element<String>("name")
        element<String>("cityName")
        element<Double>("latitude")
        element<Double>("longitude")
        element<String>("timezone")
        element<Double?>("temperature")
        element<String?>("weatherDescription")
    }

    /**
     * Serializes a City object to JSON.
     * 
     * @param encoder The encoder to write to
     * @param value The City object to serialize
     */
    override fun serialize(encoder: Encoder, value: City) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.name)
            encodeStringElement(descriptor, 1, value.cityName)
            encodeDoubleElement(descriptor, 2, value.latitude)
            encodeDoubleElement(descriptor, 3, value.longitude)
            encodeStringElement(descriptor, 4, value.timezone)
            value.temperature?.let { encodeDoubleElement(descriptor, 5, it) }
            value.weatherDescription?.let { encodeStringElement(descriptor, 6, it) }
        }
    }

    /**
     * Deserializes a City object from JSON.
     * 
     * @param decoder The decoder to read from
     * @return The deserialized City object
     */
    override fun deserialize(decoder: Decoder): City {
        var name = ""
        var latitude = 0.0
        var longitude = 0.0
        var temperature: Double? = null
        var weatherDescription: String? = null

        decoder.decodeStructure(descriptor) {
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> name = decodeStringElement(descriptor, 0)
                    2 -> latitude = decodeDoubleElement(descriptor, 2)
                    3 -> longitude = decodeDoubleElement(descriptor, 3)
                    5 -> temperature = decodeDoubleElement(descriptor, 5)
                    6 -> weatherDescription = decodeStringElement(descriptor, 6)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> if (index != CompositeDecoder.UNKNOWN_NAME) {
                        // Skip unknown elements
                        decodeElementIndex(descriptor)
                    }
                }
            }
        }

        val city = City.valueOf(name)

        // Update the city with the deserialized values
        if (latitude != 0.0 || longitude != 0.0) {
            city.updateCoordinates(latitude, longitude)
        }

        if (temperature != null) {
            // Create a minimal WeatherResponse to update the weather data
            val weatherResponse = WeatherResponse(
                weather = listOfNotNull(
                    weatherDescription?.let {
                        Weather(it)
                    }
                ),
                main = MainWeatherData(
                    temp = temperature
                )
            )
            city.updateWeatherData(weatherResponse)
        }

        return city
    }
}
