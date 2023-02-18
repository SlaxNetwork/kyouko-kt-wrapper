package io.github.slaxnetwork.kyouko

import io.github.slaxnetwork.kyouko.services.v1.IconService
import io.github.slaxnetwork.kyouko.services.v1.ProfileService
import io.github.slaxnetwork.kyouko.services.v1.RankService
import io.github.slaxnetwork.kyouko.utils.JSON
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

class KyoukoAPI(
    private val secret: String
) {
    private val client = createClient()

    val profiles = ProfileService(client)
    val ranks = RankService(client)
    val icons = IconService(client)

    private fun createClient() = HttpClient(CIO) {
        install(HttpRequestRetry) {
            maxRetries = 3
            retryIf { request, response ->
                !response.status.isSuccess()
            }
            delayMillis {
                // 0.5s per retry because this takes too long to fail.
                500L
            }
            modifyRequest { request ->
                request.headers.append("x-retry-count", retryCount.toString())
            }
        }

        install(ContentNegotiation) {
            json(JSON)
        }

        defaultRequest {
            host = System.getenv("API_HOST") ?: "0.0.0.0"
            port = runCatching {
                System.getenv("API_PORT").toInt()
            }.getOrNull() ?: 3000

            url {
                protocol = URLProtocol.HTTP
            }

            bearerAuth(secret)

            contentType(ContentType.Application.Json)
        }
    }
}