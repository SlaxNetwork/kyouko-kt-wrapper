package io.github.slaxnetwork.kyouko.models.rank

import kotlinx.serialization.Serializable

@Serializable
data class Rank(
    val id: String,
    val name: String,
    val prefixId: String,
    val color: String
)