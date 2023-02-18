package io.github.slaxnetwork.kyouko.services.v1

import io.github.slaxnetwork.kyouko.models.icon.Icon
import io.github.slaxnetwork.kyouko.utils.bodyAsResult
import io.ktor.client.*
import io.ktor.client.request.*

class IconService(
    private val client: HttpClient
) {
    suspend fun getAll(): Result<List<Icon>> {
        return client.get {
            url("/v1/icons")
        }.bodyAsResult()
    }
}