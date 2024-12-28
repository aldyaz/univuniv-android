package com.aldyaz.univuniv.domain.mapper

import com.aldyaz.univuniv.domain.model.UniversityDomainModel
import com.aldyaz.univuniv.source.local.model.UniversityDbModel

class UniversityToDbMapper : (UniversityDomainModel) -> UniversityDbModel {

    override fun invoke(p1: UniversityDomainModel): UniversityDbModel {
        return UniversityDbModel(
            name = p1.name,
            country = p1.country,
            domains = p1.domains,
            webPages = p1.webPages
        )
    }
}