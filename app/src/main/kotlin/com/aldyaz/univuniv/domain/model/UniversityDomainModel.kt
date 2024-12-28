package com.aldyaz.univuniv.domain.model

data class UniversityDomainModel(
    val name: String,
    val country: String,
    val domains: List<String>,
    val webPages: List<String>
)