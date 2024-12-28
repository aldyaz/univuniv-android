package com.aldyaz.univuniv.domain.mapper

import com.aldyaz.univuniv.core.domain.CloudApiException
import com.aldyaz.univuniv.core.domain.DomainException
import com.aldyaz.univuniv.core.domain.UnknownException
import com.aldyaz.univuniv.core.network.exception.HttpException
import com.aldyaz.univuniv.core.network.exception.HttpStatusException

class ExceptionToDomainMapper : (Exception) -> DomainException {

    override fun invoke(p1: Exception): DomainException {
        return when (p1) {
            is HttpException -> {
                when (p1.cause) {
                    is HttpStatusException -> CloudApiException(p1.message)
                    else -> UnknownException(p1.message)
                }
            }

            else -> UnknownException()
        }
    }
}