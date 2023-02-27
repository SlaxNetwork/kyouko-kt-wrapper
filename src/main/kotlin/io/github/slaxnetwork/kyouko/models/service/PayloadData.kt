package io.github.slaxnetwork.kyouko.models.service

import kotlinx.serialization.Serializable

@Serializable
data class PayloadData<T>(
    val error: Boolean,
    val data: T? = null
)