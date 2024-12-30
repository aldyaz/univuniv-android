package com.aldyaz.univuniv.domain.interactor

import com.aldyaz.univuniv.core.domain.FlowUseCase
import com.aldyaz.univuniv.domain.model.UniversityDomainModel
import com.aldyaz.univuniv.domain.repository.UniversityRepository
import kotlinx.coroutines.flow.Flow

class GetUniversitiesUseCase(
    private val repository: UniversityRepository
) : FlowUseCase<Unit, List<UniversityDomainModel>>() {

    override fun execute(param: Unit): Flow<List<UniversityDomainModel>> {
        return repository.getUniversities()
    }
}