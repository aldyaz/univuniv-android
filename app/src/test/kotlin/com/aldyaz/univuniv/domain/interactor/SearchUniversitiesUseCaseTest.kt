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
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SearchUniversitiesUseCaseTest {

    private lateinit var useCase: SearchUniversitiesUseCase

    @Test
    fun `search universities should return certain list`() = runTest {
        val repository = FakeUniversityRepository()
        useCase = SearchUniversitiesUseCase(repository)
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
        val repository = FakeUniversityRepository()
        useCase = SearchUniversitiesUseCase(repository)
        repository.dummyList = emptyList()
        useCase("").test {
            val expected = emptyList<UniversityDomainModel>()
            val actual = awaitItem()
            awaitComplete()
            Assertions.assertTrue(actual.isEmpty())
            Assertions.assertEquals(expected, actual)
        }
    }

    @Test
    fun `search universities should throw cloud api exception`() = runTest {
        val repository = FakeCloudApiExceptionUniversityRepository()
        useCase = SearchUniversitiesUseCase(repository)
        useCase("").test {
            val exception = awaitError()
            Assertions.assertTrue(exception is CloudApiException)
            Assertions.assertEquals("api error!", (exception as CloudApiException).errorMessage)
        }
    }

    @Test
    fun `search universities should throw unknown exception`() = runTest {
        val repository = FakeUnknownExceptionUniversityRepository()
        useCase = SearchUniversitiesUseCase(repository)
        useCase("").test {
            val exception = awaitError()
            Assertions.assertTrue(exception is UnknownException)
            Assertions.assertEquals("Unknown exception!", exception.message)
        }
    }
}