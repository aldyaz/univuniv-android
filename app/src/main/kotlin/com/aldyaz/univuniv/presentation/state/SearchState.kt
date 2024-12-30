package com.aldyaz.univuniv.presentation.state

import com.aldyaz.univuniv.core.presentation.ErrorPresentationModel
import com.aldyaz.univuniv.presentation.model.UniversityPresentationModel

data class SearchState(
    val loading: Boolean = true,
    val error: ErrorPresentationModel? = null,
    val data: List<UniversityPresentationModel> = emptyList()
) {

    val isError = error != null
    val isSuccess = !loading && error == null

    companion object {
        val Initial = SearchState()
    }
}