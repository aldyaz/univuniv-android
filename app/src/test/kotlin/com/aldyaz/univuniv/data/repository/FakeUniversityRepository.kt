package com.aldyaz.univuniv.data.repository

import com.aldyaz.univuniv.domain.model.UniversityDomainModel
import com.aldyaz.univuniv.domain.repository.UniversityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeUniversityRepository : UniversityRepository {

    var dummyList = listOf(
        UniversityDomainModel(
            name = "Universitas Negeri Malang",
            country = "Indonesia",
            domains = listOf("unm.ac.id"),
            webPages = listOf("https://unm.ac.id")
        ),
        UniversityDomainModel(
            name = "Binus University",
            country = "Indonesia",
            domains = listOf("binus.ac.id"),
            webPages = listOf("https://binus.ac.id")
        ),
        UniversityDomainModel(
            name = "Universitas Indonesia",
            country = "Indonesia",
            domains = listOf("ui.ac.id"),
            webPages = listOf("https://ui.ac.id")
        ),
    )

    override fun getUniversities(): Flow<List<UniversityDomainModel>> = flowOf(emptyList())

    override fun searchUniversities(query: String): Flow<List<UniversityDomainModel>> = flowOf(
        dummyList
    )
}