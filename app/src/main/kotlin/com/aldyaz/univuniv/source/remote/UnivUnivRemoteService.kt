package com.aldyaz.univuniv.source.remote

import com.aldyaz.univuniv.source.remote.model.UniversitiesDto

interface UnivUnivRemoteService {

    suspend fun getUniversities(): List<UniversitiesDto>

}