package com.aldyaz.univuniv.domain.interactor

import app.cash.turbine.test
import com.aldyaz.univuniv.data.repository.FakeUniversityRepository
import kotlinx.coroutines.runBlocking
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
    fun `get universities, should return certain list`() = runBlocking {
        useCase(Unit).test {
            val expected = repository.dummyList()
            val actual = awaitItem()
            Assertions.assertEquals(expected.first(), actual.first())
        }
    }
}