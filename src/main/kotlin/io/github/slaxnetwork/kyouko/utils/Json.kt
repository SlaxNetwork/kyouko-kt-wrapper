package io.github.slaxnetwork.kyouko.utils

import io.github.slaxnetwork.kyouko.utils.serializer.InstantSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.time.Instant
import java.util.*

internal val JSON = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true

    serializersModule = SerializersModule {
        contextual(UUID::class, UUIDSerializer)
        contextual(Instant::class, InstantSerializer)
    }
}

internal object UUIDSerializer : KSerializer<UUID> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID {
        var str = decoder.decodeString()

        if(str.length == 32) {
            val sb = StringBuilder(str)
            sb.insert(8, "-")
            sb.insert(13, "-")
            sb.insert(18, "-")
            sb.insert(23, "-")

            str = sb.toString()
        }

        return UUID.fromString(str)
    }

    override fun serialize(encoder: Encoder, value: UUID) {
        encoder.encodeString(value.toString())
    }
}