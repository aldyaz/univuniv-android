package com.aldyaz.univuniv.core.network.exception

class InternalServerException {

    companion object : HttpStatusException(
        code = 500
    )

}