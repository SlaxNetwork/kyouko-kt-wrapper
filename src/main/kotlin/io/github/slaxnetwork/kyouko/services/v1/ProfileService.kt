package io.github.slaxnetwork.kyouko.services.v1

import io.github.slaxnetwork.kyouko.models.profile.Profile
import io.github.slaxnetwork.kyouko.utils.EmptyBody
import io.github.slaxnetwork.kyouko.utils.bodyAsResult
import io.github.slaxnetwork.kyouko.utils.emptyBody
import io.ktor.client.*
import io.ktor.client.request.*
import java.util.UUID

class ProfileService(
    private val client: HttpClient
) {
    suspend fun findByUUID(uuid: UUID): Result<Profile> {
        return client.get {
            url("/v1/profiles")
            parameter("uuid", uuid.toString())
        }.bodyAsResult()
    }

    suspend fun updateLanguage(uuid: UUID, language: String): Result<Unit> {
        return client.patch {
            url("v1/profiles/language")
            parameter("uuid", uuid.toString())
            parameter("language", language)
        }.emptyBody()
    }
}