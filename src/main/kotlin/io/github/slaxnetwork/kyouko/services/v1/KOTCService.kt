package io.github.slaxnetwork.kyouko.services.v1

import io.github.slaxnetwork.kyouko.client
import io.github.slaxnetwork.kyouko.models.game.kotc.KOTCGameSession
import io.github.slaxnetwork.kyouko.utils.bodyAsResult
import io.ktor.client.request.*

object KOTCService {
    suspend fun newSession(): Result<KOTCGameSession> {
        return client.post {
            url("/v1/sessions")
        }.bodyAsResult()
    }
}