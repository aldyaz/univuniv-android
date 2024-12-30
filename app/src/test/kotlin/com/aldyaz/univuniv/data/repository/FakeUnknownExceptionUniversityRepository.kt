package com.aldyaz.univuniv.data.repository

import com.aldyaz.univuniv.core.domain.exception.UnknownException
import com.aldyaz.univuniv.domain.model.UniversityDomainModel
import com.aldyaz.univuniv.domain.repository.UniversityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeUnknownExceptionUniversityRepository : UniversityRepository {

    override fun getUniversities(): Flow<List<UniversityDomainModel>> = flow {
        throw UnknownException()
    }
}