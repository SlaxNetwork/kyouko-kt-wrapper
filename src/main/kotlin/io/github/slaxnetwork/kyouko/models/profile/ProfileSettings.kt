package io.github.slaxnetwork.kyouko.models.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileSettings(
    @SerialName("language")
    val language: String
)
