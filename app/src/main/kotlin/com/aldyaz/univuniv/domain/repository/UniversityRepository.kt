package com.aldyaz.univuniv.domain.repository

import com.aldyaz.univuniv.domain.model.UniversityDomainModel
import kotlinx.coroutines.flow.Flow

interface UniversityRepository {

    fun getUniversities(): Flow<List<UniversityDomainModel>>

    fun searchUniversities(query: String): Flow<List<UniversityDomainModel>>

}