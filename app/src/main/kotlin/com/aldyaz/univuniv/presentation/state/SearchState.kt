package com.aldyaz.univuniv.presentation.state

import com.aldyaz.univuniv.presentation.model.UniversityPresentationModel

data class SearchState(
    val data: List<UniversityPresentationModel> = emptyList()
)