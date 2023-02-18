package io.github.slaxnetwork.kyouko.models.service

import kotlinx.serialization.Serializable

@Serializable
data class RouteError(
    val error: Boolean,
    override val message: String,
    val httpCode: Int
) : RuntimeException()
