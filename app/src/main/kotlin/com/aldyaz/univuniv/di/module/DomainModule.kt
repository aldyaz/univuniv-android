package com.aldyaz.univuniv.di.module

import com.aldyaz.univuniv.data.local.UniversityLocalDataSource
import com.aldyaz.univuniv.data.remote.UniversityRemoteDataSource
import com.aldyaz.univuniv.data.repository.UniversityRepositoryImpl
import com.aldyaz.univuniv.domain.mapper.ExceptionToDomainMapper
import com.aldyaz.univuniv.domain.repository.UniversityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

    @Provides
    fun provideExceptionToDomainMapper(): ExceptionToDomainMapper = ExceptionToDomainMapper()

    @Provides
    fun provideUniversityRepository(
        localDataSource: UniversityLocalDataSource,
        remoteDataSource: UniversityRemoteDataSource
    ): UniversityRepository = UniversityRepositoryImpl(
        localDataSource = localDataSource,
        remoteDataSource = remoteDataSource
    )
}