package com.aldyaz.univuniv.core.network

import android.util.Log
import com.aldyaz.univuniv.core.network.exception.BadRequestException
import com.aldyaz.univuniv.core.network.exception.HttpException
import com.aldyaz.univuniv.core.network.exception.HttpStatusException
import com.aldyaz.univuniv.core.network.exception.InternalServerException
import com.pluto.plugins.network.ktor.PlutoKtorInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpClientFactory {

    fun create(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        useAlternativeNames = true
                        ignoreUnknownKeys = true
                        encodeDefaults = false
                    }
                )
            }

            Logging {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d(this::class.java.canonicalName, message)
                    }
                }
                level = LogLevel.ALL
            }

            install(PlutoKtorInterceptor)

            HttpResponseValidator {
                handleResponseExceptionWithRequest { cause, _ ->
                    val clientException = cause as? ClientRequestException
                        ?: return@handleResponseExceptionWithRequest
                    val exceptionResponse = clientException.response
                    val status = exceptionResponse.status
                    throw HttpException(
                        message = exceptionResponse.bodyAsText().ifEmpty {
                            HttpException.DEFAULT_ERROR_MESSAGE
                        },
                        cause = when (status) {
                            HttpStatusCode.BadRequest -> BadRequestException
                            HttpStatusCode.InternalServerError -> InternalServerException
                            else -> HttpStatusException(
                                code = status.value
                            )
                        }
                    )
                }
            }

            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                url {
                    takeFrom("http://universities.hipolabs.com")
                    path("search")
                    parameters.append("country", "indonesia")
                }
            }
        }
    }
}