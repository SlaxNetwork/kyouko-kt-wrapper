package io.github.slaxnetwork.kyouko.models.icon

import kotlinx.serialization.Serializable

@Serializable
data class Icon(
    val name: String,
    val char: Char
)
