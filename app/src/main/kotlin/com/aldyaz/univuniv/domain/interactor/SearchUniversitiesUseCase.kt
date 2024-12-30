package com.aldyaz.univuniv.domain.interactor

import com.aldyaz.univuniv.core.domain.FlowUseCase
import com.aldyaz.univuniv.domain.model.UniversityDomainModel
import com.aldyaz.univuniv.domain.repository.UniversityRepository
import kotlinx.coroutines.flow.Flow

class SearchUniversitiesUseCase(
    private val repository: UniversityRepository
) : FlowUseCase<String, List<UniversityDomainModel>>() {

    override fun execute(param: String): Flow<List<UniversityDomainModel>> {
        return repository.searchUniversities(param)
    }
}