package com.aldyaz.univuniv.data.repository

import com.aldyaz.univuniv.data.local.UniversityLocalDataSource
import com.aldyaz.univuniv.data.remote.UniversityRemoteDataSource
import com.aldyaz.univuniv.domain.mapper.ExceptionToDomainMapper
import com.aldyaz.univuniv.domain.repository.UniversityRepository

class UniversityRepositoryImpl(
    private val localDataSource: UniversityLocalDataSource,
    private val remoteDataSource: UniversityRemoteDataSource,
    private val exceptionToDomainMapper: ExceptionToDomainMapper
) : UniversityRepository {
}