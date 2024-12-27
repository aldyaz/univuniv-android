package com.aldyaz.univuniv.core.network.exception

class BadRequestException {

    companion object : HttpStatusException(
        code = 400
    )
}