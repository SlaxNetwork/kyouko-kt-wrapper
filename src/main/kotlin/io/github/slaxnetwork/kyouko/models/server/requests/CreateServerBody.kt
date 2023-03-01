package io.github.slaxnetwork.kyouko.models.server.requests

import kotlinx.serialization.Serializable

@Serializable
internal data class CreateServerBody(
    val ip: String,
    val port: Int,
    val type: String
)