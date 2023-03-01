package io.github.slaxnetwork.kyouko.models.server

import kotlinx.serialization.Serializable

@Serializable
data class ServerInstance(
    val ip: String,
    val port: Int,
    val type: String? = null
)