package com.aldyaz.univuniv.domain.interactor

import app.cash.turbine.test
import com.aldyaz.univuniv.data.repository.FakeUniversityRepository
import com.aldyaz.univuniv.domain.model.UniversityDomainModel
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SearchUniversitiesUseCaseTest {

    private lateinit var repository: FakeUniversityRepository
    private lateinit var useCase: SearchUniversitiesUseCase

    @BeforeEach
    fun setup() {
        repository = FakeUniversityRepository()
        useCase = SearchUniversitiesUseCase(repository)
    }

    @Test
    fun `search universities should return certain list`() = runTest {
        useCase("").test {
            val expected = repository.dummyList
            val actual = awaitItem()
            awaitComplete()
            Assertions.assertEquals(expected.size, actual.size)
            Assertions.assertEquals(expected.first(), actual.first())
            Assertions.assertEquals(expected.first().name, actual.first().name)
        }
    }

    @Test
    fun `search universities should return empty list`() = runTest {
        repository.dummyList = emptyList()
        useCase("").test {
            val expected = emptyList<UniversityDomainModel>()
            val actual = awaitItem()
            awaitComplete()
            Assertions.assertTrue(actual.isEmpty())
            Assertions.assertEquals(expected, actual)
        }
    }
}