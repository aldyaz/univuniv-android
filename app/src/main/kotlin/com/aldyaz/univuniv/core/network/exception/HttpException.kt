package com.aldyaz.univuniv.core.network.exception

import java.io.IOException

class HttpException(
    override val message: String,
    override val cause: Throwable
) : IOException() {

    companion object {
        const val DEFAULT_ERROR_MESSAGE = "HTTP exception!"
    }
}