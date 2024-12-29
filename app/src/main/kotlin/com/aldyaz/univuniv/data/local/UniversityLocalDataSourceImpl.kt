package com.aldyaz.univuniv.data.local

import com.aldyaz.univuniv.core.data.exception.EmptyResultException
import com.aldyaz.univuniv.source.local.dao.UniversityDao
import com.aldyaz.univuniv.source.local.model.UniversityDbModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UniversityLocalDataSourceImpl @Inject constructor(
    private val dao: UniversityDao
) : UniversityLocalDataSource {

    override fun getUniversities(): Flow<List<UniversityDbModel>> {
        return flow {
            if (dao.isTableEmpty()) {
                throw EmptyResultException()
            }
            emitAll(dao.getUniversities())
        }
    }

    override fun getUniversitiesByName(name: String): Flow<List<UniversityDbModel>> {
        return dao.getUniversitiesByName(name)
    }

    override suspend fun saveUniversities(items: List<UniversityDbModel>) {
        dao.saveUniversities(items)
    }
}