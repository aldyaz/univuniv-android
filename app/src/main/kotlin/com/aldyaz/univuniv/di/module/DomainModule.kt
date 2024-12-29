package com.aldyaz.univuniv.di.module

import com.aldyaz.univuniv.data.local.UniversityLocalDataSource
import com.aldyaz.univuniv.data.remote.UniversityRemoteDataSource
import com.aldyaz.univuniv.data.repository.UniversityRepositoryImpl
import com.aldyaz.univuniv.domain.interactor.GetUniversitiesUseCase
import com.aldyaz.univuniv.domain.mapper.ExceptionToDomainMapper
import com.aldyaz.univuniv.domain.mapper.UniversityDbToDomainMapper
import com.aldyaz.univuniv.domain.mapper.UniversityDtoToDomainMapper
import com.aldyaz.univuniv.domain.mapper.UniversityToDbMapper
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
    fun provideUniversityDtoToDomainMapper(): UniversityDtoToDomainMapper =
        UniversityDtoToDomainMapper()

    @Provides
    fun provideUniversityDbToDomainMapper(): UniversityDbToDomainMapper =
        UniversityDbToDomainMapper()

    @Provides
    fun provideUniversityToDbMapper(): UniversityToDbMapper = UniversityToDbMapper()

    @Provides
    fun provideUniversityRepository(
        localDataSource: UniversityLocalDataSource,
        remoteDataSource: UniversityRemoteDataSource,
        universityDtoToDomainMapper: UniversityDtoToDomainMapper,
        universityToDbMapper: UniversityToDbMapper,
        universityDbToDomainMapper: UniversityDbToDomainMapper,
        exceptionToDomainMapper: ExceptionToDomainMapper
    ): UniversityRepository = UniversityRepositoryImpl(
        localDataSource = localDataSource,
        remoteDataSource = remoteDataSource,
        universityDtoToDomainMapper = universityDtoToDomainMapper,
        universityToDbMapper = universityToDbMapper,
        universityDbToDomainMapper = universityDbToDomainMapper,
        exceptionToDomainMapper = exceptionToDomainMapper
    )

    @Provides
    fun provideGetUniversitiesUseCase(
        universityRepository: UniversityRepository
    ): GetUniversitiesUseCase = GetUniversitiesUseCase(universityRepository)
}