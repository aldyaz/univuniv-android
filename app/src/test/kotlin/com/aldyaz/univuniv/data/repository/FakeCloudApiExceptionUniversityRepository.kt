package com.aldyaz.univuniv.data.repository

import com.aldyaz.univuniv.core.domain.exception.CloudApiException
import com.aldyaz.univuniv.domain.model.UniversityDomainModel
import com.aldyaz.univuniv.domain.repository.UniversityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCloudApiExceptionUniversityRepository : UniversityRepository {

    override fun getUniversities(): Flow<List<UniversityDomainModel>> = flow {
        throw CloudApiException("api error!")
    }
}