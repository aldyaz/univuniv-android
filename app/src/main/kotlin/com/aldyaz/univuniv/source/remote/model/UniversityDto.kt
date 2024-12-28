package com.aldyaz.univuniv.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UniversityDto(
    @SerialName("country")
    val country: String? = null,
    @SerialName("state-province")
    val stateProvince: String? = null,
    @SerialName("alpha_two_code")
    val alphaTwoCode: String? = null,
    @SerialName("domains")
    val domains: List<String>? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("web_pages")
    val webPages: List<String>? = null
)