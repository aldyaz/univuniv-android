package com.aldyaz.univuniv.data.local

import com.aldyaz.univuniv.source.local.model.UniversityDbModel
import kotlinx.coroutines.flow.Flow

interface UniversityLocalDataSource {

    fun getUniversities(): Flow<List<UniversityDbModel>>

    fun getUniversitiesByName(name: String): Flow<List<UniversityDbModel>>

    suspend fun saveUniversities(items: List<UniversityDbModel>)

}