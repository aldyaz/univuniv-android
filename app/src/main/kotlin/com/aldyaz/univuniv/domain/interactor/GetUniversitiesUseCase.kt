package com.aldyaz.univuniv.domain.interactor

import com.aldyaz.univuniv.core.domain.FlowUseCase
import com.aldyaz.univuniv.domain.model.UniversityDomainModel
import com.aldyaz.univuniv.domain.repository.UniversityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class GetUniversitiesUseCase(
    private val repository: UniversityRepository
) : FlowUseCase<Unit, List<UniversityDomainModel>>() {

    override fun execute(param: Unit): Flow<List<UniversityDomainModel>> {
        return repository.getLocalUniversities().flatMapMerge { items ->
            if (items.isNotEmpty()) {
                flowOf(items)
            } else {
                repository.getRemoteUniversities().map {
                    repository.saveUniversities(it)
                    it
                }
            }
        }
    }
}