package com.aldyaz.univuniv.presentation.state

import com.aldyaz.univuniv.core.presentation.ErrorPresentationModel
import com.aldyaz.univuniv.presentation.model.UniversityPresentationModel

data class HomeState(
    val loading: Boolean = true,
    val error: ErrorPresentationModel? = null,
    val data: List<UniversityPresentationModel> = emptyList()
) {

    val success = !loading && error == null

    companion object {
        val Initial = HomeState()
    }
}