package io.github.slaxnetwork.kyouko.utils

import io.github.slaxnetwork.kyouko.models.service.PayloadData
import io.github.slaxnetwork.kyouko.models.service.RouteError
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*

internal suspend inline fun <reified T> HttpResponse.bodyAsResult(): Result<T> {
    if(!status.isSuccess()) {
        return Result.failure(body<RouteError>())
    }

    return Result.success(body<PayloadData<T>>().data!!)
}

internal suspend inline fun <reified T> HttpResponse.bodyAsNullableResult(): Result<T?> {
    if(!status.isSuccess()) {
        return Result.failure(body<RouteError>())
    }

    return Result.success(body<PayloadData<T>>().data)
}

internal suspend inline fun HttpResponse.emptyBody(): Result<Unit> {
    if(!status.isSuccess()) {
        return Result.failure(body<RouteError>())
    }

    return Result.success(Unit)
}

/**
 * Represents a response that will have no `data` field sent along
 * with the response and is purely just to check whether it was a success or not.
 */
// amazing.
internal typealias EmptyBody = Void