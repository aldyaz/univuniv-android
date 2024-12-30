package com.aldyaz.univuniv.presentation.viewmodel

import app.cash.turbine.test
import com.aldyaz.univuniv.core.domain.exception.CloudApiException
import com.aldyaz.univuniv.core.domain.exception.UnknownException
import com.aldyaz.univuniv.core.presentation.ErrorPresentationModel
import com.aldyaz.univuniv.core.presentation.ExceptionToPresentationMapper
import com.aldyaz.univuniv.domain.interactor.GetUniversitiesUseCase
import com.aldyaz.univuniv.domain.model.UniversityDomainModel
import com.aldyaz.univuniv.presentation.intent.HomeIntent
import com.aldyaz.univuniv.presentation.mapper.UniversitiesToPresentationMapper
import com.aldyaz.univuniv.presentation.model.UniversityPresentationModel
import com.aldyaz.univuniv.presentation.state.HomeState
import com.aldyaz.univuniv.utils.CoroutineTestExtension
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CoroutineTestExtension::class)
class HomeViewModelTest {

    private val getUniversitiesUseCase: GetUniversitiesUseCase = mockk(relaxed = true)
    private val universitiesToPresentationMapper: UniversitiesToPresentationMapper =
        mockk(relaxed = true)
    private val exceptionToPresentationMapper: ExceptionToPresentationMapper = mockk(relaxed = true)

    private lateinit var viewModel: HomeViewModel

    @BeforeEach
    fun setup() {
        viewModel = HomeViewModel(
            getUniversitiesUseCase = getUniversitiesUseCase,
            universitiesToPresentationMapper = universitiesToPresentationMapper,
            exceptionToPresentationMapper = exceptionToPresentationMapper
        )
    }

    @Test
    fun `check initial state are correct`() = runTest {
        viewModel.state.test {
            val initialState = awaitItem()
            Assertions.assertEquals(HomeState.Initial, initialState)
        }
    }

    @Test
    fun `invoke fetch universities should return university list`() = runTest {
        viewModel.state.test {
            val expected = dummyPresentationList()

            awaitItem()

            every { getUniversitiesUseCase(Unit) }.returns(flowOf(dummyDomainList()))
            every { universitiesToPresentationMapper(dummyDomainList()) }.returns(expected)

            viewModel.onIntent(HomeIntent.Fetch)

            val resultState = awaitItem()
            Assertions.assertEquals(expected.size, resultState.data.size)
        }
    }

    @Test
    fun `invoke fetch universities should return cloud api error`() = runTest {
        viewModel.state.test {
            val errMessage = "api error!"
            val exception = CloudApiException(errMessage)
            val expected = ErrorPresentationModel.CloudApiError(errMessage)

            awaitItem()

            every { getUniversitiesUseCase(Unit) }.returns(
                flow {
                    throw exception
                }
            )
            every { exceptionToPresentationMapper(exception) } returns expected

            viewModel.onIntent(HomeIntent.Fetch)

            val resultState = awaitItem()
            Assertions.assertEquals(expected, resultState.error)
            Assertions.assertEquals(expected.message, resultState.error?.errorMessage)
        }
    }

    @Test
    fun `invoke fetch universities should return unknown error`() = runTest {
        viewModel.state.test {
            val errMessage = "unknown error!"
            val exception = UnknownException(errMessage)
            val expected = ErrorPresentationModel.Unknown(errMessage)

            awaitItem()

            every { getUniversitiesUseCase(Unit) }.returns(
                flow {
                    throw exception
                }
            )
            every { exceptionToPresentationMapper(exception) } returns expected

            viewModel.onIntent(HomeIntent.Fetch)

            val resultState = awaitItem()
            Assertions.assertEquals(expected, resultState.error)
            Assertions.assertEquals(expected.message, resultState.error?.errorMessage)
        }
    }

    private fun dummyPresentationList() = listOf(
        UniversityPresentationModel(
            name = "Universitas Negeri Malang",
            country = "Indonesia",
            domains = listOf("unm.ac.id"),
            webPages = listOf("https://unm.ac.id")
        ),
        UniversityPresentationModel(
            name = "Binus University",
            country = "Indonesia",
            domains = listOf("binus.ac.id"),
            webPages = listOf("https://binus.ac.id")
        ),
        UniversityPresentationModel(
            name = "Universitas Indonesia",
            country = "Indonesia",
            domains = listOf("ui.ac.id"),
            webPages = listOf("https://ui.ac.id")
        )
    )

    private fun dummyDomainList() = listOf(
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
        )
    )

}
