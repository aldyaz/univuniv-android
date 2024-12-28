package com.aldyaz.univuniv.domain.mapper

import com.aldyaz.univuniv.domain.model.UniversityDomainModel
import com.aldyaz.univuniv.source.local.model.UniversityDbModel

class UniversityDbToDomainMapper : (UniversityDbModel) -> UniversityDomainModel {

    override fun invoke(p1: UniversityDbModel): UniversityDomainModel {
        return UniversityDomainModel(
            name = p1.name,
            country = p1.country,
            domains = listOf(),
            webPages = listOf()
        )
    }
}