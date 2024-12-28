package com.aldyaz.univuniv.data.remote

import com.aldyaz.univuniv.core.network.HttpResult
import com.aldyaz.univuniv.core.network.parseHttp
import com.aldyaz.univuniv.source.remote.UnivUnivRemoteService
import com.aldyaz.univuniv.source.remote.model.UniversitiesDto
import javax.inject.Inject

class UniversityRemoteDataSourceImpl @Inject constructor(
    private val service: UnivUnivRemoteService
) : UniversityRemoteDataSource {

    override suspend fun getUniversities(): HttpResult<List<UniversitiesDto>> {
        return parseHttp {
            service.getUniversities()
        }
    }
}