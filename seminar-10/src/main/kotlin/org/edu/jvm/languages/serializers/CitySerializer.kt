package org.edu.jvm.languages.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.descriptors.nullable
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import org.edu.jvm.languages.models.City

object CitySerializer : KSerializer<City> {
    override val descriptor: SerialDescriptor = TODO()

    override fun serialize(encoder: Encoder, value: City) = TODO()

    override fun deserialize(decoder: Decoder): City = TODO()
}
