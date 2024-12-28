package com.aldyaz.univuniv.data.repository

import com.aldyaz.univuniv.core.network.HttpResult
import com.aldyaz.univuniv.data.local.UniversityLocalDataSource
import com.aldyaz.univuniv.data.remote.UniversityRemoteDataSource
import com.aldyaz.univuniv.domain.mapper.ExceptionToDomainMapper
import com.aldyaz.univuniv.domain.mapper.UniversityDbToDomainMapper
import com.aldyaz.univuniv.domain.mapper.UniversityDtoToDomainMapper
import com.aldyaz.univuniv.domain.mapper.UniversityToDbMapper
import com.aldyaz.univuniv.domain.model.UniversityDomainModel
import com.aldyaz.univuniv.domain.repository.UniversityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class UniversityRepositoryImpl(
    private val localDataSource: UniversityLocalDataSource,
    private val remoteDataSource: UniversityRemoteDataSource,
    private val universityDtoToDomainMapper: UniversityDtoToDomainMapper,
    private val universityToDbMapper: UniversityToDbMapper,
    private val universityDbToDomainMapper: UniversityDbToDomainMapper,
    private val exceptionToDomainMapper: ExceptionToDomainMapper
) : UniversityRepository {

    override fun getRemoteUniversities(): Flow<List<UniversityDomainModel>> {
        return flow {
            when (val result = remoteDataSource.getUniversities()) {
                is HttpResult.Success -> {
                    val items = result.data
                    emit(
                        List(items.size) {
                            universityDtoToDomainMapper(items[it])
                        }
                    )
                }

                is HttpResult.Error -> throw exceptionToDomainMapper(result.exception)
            }
        }
    }

    override fun getLocalUniversities(): Flow<List<UniversityDomainModel>> {
        return localDataSource.getUniversities().map { items ->
            List(items.size) {
                universityDbToDomainMapper(items[it])
            }
        }
    }

    override fun saveUniversities(items: List<UniversityDomainModel>): Flow<Unit> {
        return flow {
            emit(
                localDataSource.saveUniversities(
                    items.map(universityToDbMapper)
                )
            )
        }
    }
}