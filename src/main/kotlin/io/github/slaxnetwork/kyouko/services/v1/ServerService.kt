package io.github.slaxnetwork.kyouko.services.v1

import io.github.slaxnetwork.kyouko.client
import io.github.slaxnetwork.kyouko.models.server.ServerInstance
import io.github.slaxnetwork.kyouko.models.server.requests.CreateServerBody
import io.github.slaxnetwork.kyouko.utils.bodyAsResult
import io.github.slaxnetwork.kyouko.utils.emptyBody
import io.ktor.client.request.*

object ServerService {
    suspend fun registerInstance(ip: String, port: Int, type: String): Result<String> {
        return client.post("/v1/servers") {
            setBody(CreateServerBody(ip, port, type))
        }.bodyAsResult()
    }

    suspend fun unregisterInstance(instanceId: String): Result<Unit> {
        return client.delete("/v1/servers") {
            parameter("instanceId", instanceId)
        }.emptyBody()
    }

    suspend fun getAll(): Result<List<ServerInstance>> {
        return client.get("/v1/servers")
            .bodyAsResult()
    }
}