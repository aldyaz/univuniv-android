package com.aldyaz.univuniv.data.remote

import com.aldyaz.univuniv.core.network.HttpResult
import com.aldyaz.univuniv.source.remote.model.UniversitiesDto

interface UniversityRemoteDataSource {

    suspend fun getUniversities(): HttpResult<List<UniversitiesDto>>

}