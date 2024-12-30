package com.aldyaz.univuniv.core.presentation

import com.aldyaz.univuniv.core.domain.exception.CloudApiException
import com.aldyaz.univuniv.core.domain.exception.DomainException

class ExceptionToPresentationMapper : (Throwable) -> ErrorPresentationModel {

    override fun invoke(p1: Throwable): ErrorPresentationModel {
        return when (p1) {
            is DomainException -> {
                when (p1) {
                    is CloudApiException -> ErrorPresentationModel.CloudApiError(p1.message)
                    else -> ErrorPresentationModel.Unknown(p1.message ?: "Network error!")
                }
            }

            else -> ErrorPresentationModel.Unknown(
                p1.message ?: "Network error!"
            )
        }
    }
}