package io.github.slaxnetwork.kyouko

import io.github.slaxnetwork.kyouko.utils.JSON
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

internal val client = HttpClient(CIO) {
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
        host = System.getProperty("API_HOST")
            ?: System.getenv("API_HOST")
            ?: "0.0.0.0"


        port = runCatching { ( System.getProperty("API_PORT") ?: System.getenv("API_PORT") ).toInt() }
            .getOrNull()
            ?: 3000

        url {
            protocol = URLProtocol.HTTP
        }

        val secret = System.getProperty("API_SECRET")
            ?: System.getenv("API_SECRET")
            ?: "KYOUKO"
        bearerAuth(secret)

        contentType(ContentType.Application.Json)
    }
}