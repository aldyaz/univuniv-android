package com.aldyaz.univuniv.presentation.model

data class UniversityPresentationModel(
    val name: String = "",
    val country: String = "",
    val domains: List<String> = emptyList(),
    val webPages: List<String> = emptyList()
)