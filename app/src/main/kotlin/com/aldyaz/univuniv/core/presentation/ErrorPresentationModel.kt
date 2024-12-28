package com.aldyaz.univuniv.core.presentation

sealed class ErrorPresentationModel(
    val errorMessage: String
) {

    data class CloudApiError(
        val message: String
    ) : ErrorPresentationModel(message)

    data class Unknown(
        val message: String
    ) : ErrorPresentationModel(message)

}