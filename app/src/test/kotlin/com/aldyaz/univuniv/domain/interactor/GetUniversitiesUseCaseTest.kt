package com.aldyaz.univuniv.domain.interactor

import app.cash.turbine.test
import com.aldyaz.univuniv.core.domain.exception.CloudApiException
import com.aldyaz.univuniv.core.domain.exception.UnknownException
import com.aldyaz.univuniv.data.repository.FakeCloudApiExceptionUniversityRepository
import com.aldyaz.univuniv.data.repository.FakeUniversityRepository
import com.aldyaz.univuniv.data.repository.FakeUnknownExceptionUniversityRepository
import com.aldyaz.univuniv.domain.model.UniversityDomainModel
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GetUniversitiesUseCaseTest {

    private lateinit var useCase: GetUniversitiesUseCase

    @Test
    fun `get universities should return certain list`() = runTest {
        val repository = FakeUniversityRepository()
        useCase = GetUniversitiesUseCase(repository)
        useCase(Unit).test {
            val expected = repository.dummyList
            val actual = awaitItem()
            awaitComplete()
            Assertions.assertEquals(expected.size, actual.size)
            Assertions.assertEquals(expected.first(), actual.first())
            Assertions.assertEquals(expected.first().name, actual.first().name)
        }
    }

    @Test
    fun `get universities should return empty list`() = runTest {
        val repository = FakeUniversityRepository()
        useCase = GetUniversitiesUseCase(repository)
        repository.dummyList = emptyList()
        useCase(Unit).test {
            val expected = emptyList<UniversityDomainModel>()
            val actual = awaitItem()
            awaitComplete()
            Assertions.assertTrue(actual.isEmpty())
            Assertions.assertEquals(expected, actual)
        }
    }

    @Test
    fun `get universities should return cloud api exception`() = runTest {
        val repository = FakeCloudApiExceptionUniversityRepository()
        useCase = GetUniversitiesUseCase(repository)
        useCase(Unit).test {
            val exception = awaitError()
            Assertions.assertTrue(exception is CloudApiException)
            Assertions.assertEquals("api error!", (exception as CloudApiException).errorMessage)
        }
    }

    @Test
    fun `get universities should return unknown exception`() = runTest {
        val repository = FakeUnknownExceptionUniversityRepository()
        useCase = GetUniversitiesUseCase(repository)
        useCase(Unit).test {
            val exception = awaitError()
            Assertions.assertTrue(exception is UnknownException)
            Assertions.assertEquals("Unknown exception!", exception.message)
        }
    }
}
