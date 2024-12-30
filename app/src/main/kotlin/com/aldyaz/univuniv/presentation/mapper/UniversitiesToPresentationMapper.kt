package com.aldyaz.univuniv.presentation.mapper

import com.aldyaz.univuniv.domain.model.UniversityDomainModel
import com.aldyaz.univuniv.presentation.model.UniversityPresentationModel

class UniversitiesToPresentationMapper(
    private val universityToPresentationMapper: UniversityToPresentationMapper
) : (List<UniversityDomainModel>) -> List<UniversityPresentationModel> {

    override fun invoke(p1: List<UniversityDomainModel>): List<UniversityPresentationModel> {
        return List(p1.size) {
            universityToPresentationMapper(p1[it])
        }
    }
}
