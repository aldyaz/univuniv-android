package com.aldyaz.univuniv.domain.repository

import com.aldyaz.univuniv.domain.model.UniversityDomainModel
import kotlinx.coroutines.flow.Flow

interface UniversityRepository {

    fun getUniversities(name: String): Flow<List<UniversityDomainModel>>

}