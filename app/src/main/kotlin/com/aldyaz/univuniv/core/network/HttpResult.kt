package com.aldyaz.univuniv.core.network

import com.aldyaz.univuniv.core.network.exception.HttpException

sealed class HttpResult<out T : Any> {

    data class Success<out T : Any>(
        val data: T
    ) : HttpResult<T>()

    data class Error(
        val exception: HttpException
    ) : HttpResult<Nothing>()

}

suspend inline fun <T : Any> parseHttp(
    crossinline func: suspend () -> T
): HttpResult<T> = try {
    val result = func()
    HttpResult.Success(result)
} catch (err: HttpException) {
    HttpResult.Error(err)
}
