package com.aldyaz.univuniv.core.network

import com.aldyaz.univuniv.core.network.exception.HttpException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> apiCall(
    call: () -> HttpResponse
): T = try {
    val response = call()
    response.body<T>()
} catch (err: HttpException) {
    err.printStackTrace()
    throw err
}