package com.aldyaz.univuniv.domain.repository

import com.aldyaz.univuniv.domain.model.UniversityDomainModel
import kotlinx.coroutines.flow.Flow

interface UniversityRepository {

    fun getRemoteUniversities(): Flow<List<UniversityDomainModel>>

    fun getLocalUniversities(): Flow<List<UniversityDomainModel>>

    fun saveUniversities(items: List<UniversityDomainModel>): Flow<Unit>

}