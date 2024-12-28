package com.aldyaz.univuniv.domain.mapper

import com.aldyaz.univuniv.domain.model.UniversityDomainModel
import com.aldyaz.univuniv.source.remote.model.UniversityDto

class UniversityDtoToDomainMapper : (UniversityDto) -> UniversityDomainModel {

    override fun invoke(p1: UniversityDto): UniversityDomainModel {
        return UniversityDomainModel(
            name = p1.name.orEmpty(),
            country = p1.country.orEmpty(),
            domains = p1.domains.orEmpty(),
            webPages = p1.webPages.orEmpty()
        )
    }
}