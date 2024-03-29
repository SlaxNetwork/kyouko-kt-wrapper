package io.github.slaxnetwork.kyouko.services.v1

import io.github.slaxnetwork.kyouko.client
import io.github.slaxnetwork.kyouko.models.rank.Rank
import io.github.slaxnetwork.kyouko.utils.bodyAsResult
import io.ktor.client.request.*

object RankService {
    suspend fun getAll(): Result<List<Rank>> {
        return client.get {
            url("/v1/ranks")
        }.bodyAsResult()
    }
}