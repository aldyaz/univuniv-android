package com.aldyaz.univuniv.source.remote

import com.aldyaz.univuniv.source.remote.model.UniversityDto

interface UnivUnivRemoteService {

    suspend fun getUniversities(): List<UniversityDto>

}