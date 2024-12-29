package com.aldyaz.univuniv.domain.interactor

import app.cash.turbine.test
import com.aldyaz.univuniv.data.repository.FakeUniversityRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetUniversitiesUseCaseTest {

    private lateinit var repository: FakeUniversityRepository
    private lateinit var useCase: GetUniversitiesUseCase

    @BeforeEach
    fun setup() {
        repository = FakeUniversityRepository()
        useCase = GetUniversitiesUseCase(repository)
    }

    @Test
    fun `get universities, should return certain list`() = runTest {
        useCase(Unit).test {
            val repoFlow = repository.getUniversities().testIn(this)
            val expected = repository.dummyList()
            repoFlow.awaitItem()
            repoFlow.awaitComplete()
            val actual = awaitItem()
            awaitComplete()
            Assertions.assertEquals(expected.size, actual.size)
            Assertions.assertEquals(expected.first(), actual.first())
            Assertions.assertEquals(expected.first().name, actual.first().name)
        }
    }
}