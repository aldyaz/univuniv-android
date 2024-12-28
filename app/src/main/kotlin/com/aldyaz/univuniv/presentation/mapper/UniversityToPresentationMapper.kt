package com.aldyaz.univuniv.presentation.mapper

import com.aldyaz.univuniv.domain.model.UniversityDomainModel
import com.aldyaz.univuniv.presentation.model.UniversityPresentationModel

class UniversityToPresentationMapper : (UniversityDomainModel) -> UniversityPresentationModel {

    override fun invoke(p1: UniversityDomainModel): UniversityPresentationModel {
        return UniversityPresentationModel(
            name = p1.name,
            country = p1.country,
            domains = p1.domains,
            webPages = p1.webPages
        )
    }
}