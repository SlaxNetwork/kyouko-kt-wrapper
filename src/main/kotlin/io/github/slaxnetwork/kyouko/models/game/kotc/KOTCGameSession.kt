package io.github.slaxnetwork.kyouko.models.game.kotc

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class KOTCGameSession(
    val id: Int,

    @Contextual
    val createdAt: Instant,

    @Contextual
    val endedAt: Instant? = null
)