package com.aldyaz.univuniv.presentation.viewmodel

import app.cash.turbine.test
import com.aldyaz.univuniv.core.presentation.ExceptionToPresentationMapper
import com.aldyaz.univuniv.domain.interactor.GetUniversitiesUseCase
import com.aldyaz.univuniv.domain.model.UniversityDomainModel
import com.aldyaz.univuniv.presentation.mapper.UniversitiesToPresentationMapper
import com.aldyaz.univuniv.presentation.model.UniversityPresentationModel
import com.aldyaz.univuniv.presentation.state.HomeState
import com.aldyaz.univuniv.utils.CoroutineTestExtension
import io.mockk.coEvery
import io.mockk.mockk
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
