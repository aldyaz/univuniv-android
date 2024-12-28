package com.aldyaz.univuniv.data.local

import com.aldyaz.univuniv.source.local.dao.UniversityDao
import com.aldyaz.univuniv.source.local.model.UniversityDbModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UniversityLocalDataSourceImpl @Inject constructor(
    private val dao: UniversityDao
) : UniversityLocalDataSource {

    override fun getUniversities(): Flow<List<UniversityDbModel>> {
        return dao.getUniversities()
    }

    override fun getUniversitiesByName(name: String): Flow<List<UniversityDbModel>> {
        return dao.getUniversitiesByName(name)
    }

    override suspend fun saveUniversities(vararg item: UniversityDbModel) {
        dao.saveUniversities(*item)
    }
}