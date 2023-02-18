package io.github.slaxnetwork.kyouko.models.profile

import io.github.slaxnetwork.kyouko.models.rank.Rank
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Profile(
    @SerialName("id")
    @Contextual
    val uuid: UUID,

    val settings: ProfileSettings,

    @SerialName("rank")
    val rank: Rank
)