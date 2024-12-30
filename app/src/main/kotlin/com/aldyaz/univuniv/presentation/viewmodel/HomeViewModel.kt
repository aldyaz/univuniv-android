package com.aldyaz.univuniv.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.aldyaz.univuniv.core.presentation.BaseViewModel
import com.aldyaz.univuniv.core.presentation.ExceptionToPresentationMapper
import com.aldyaz.univuniv.core.util.CoroutinesContextProvider
import com.aldyaz.univuniv.domain.interactor.GetUniversitiesUseCase
import com.aldyaz.univuniv.presentation.intent.HomeIntent
import com.aldyaz.univuniv.presentation.mapper.UniversitiesToPresentationMapper
import com.aldyaz.univuniv.presentation.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUniversitiesUseCase: GetUniversitiesUseCase,
    private val universitiesToPresentationMapper: UniversitiesToPresentationMapper,
    private val exceptionToPresentationMapper: ExceptionToPresentationMapper,
    private val coroutinesContextProvider: CoroutinesContextProvider
) : BaseViewModel<HomeIntent>() {

    private val _state = MutableStateFlow(HomeState.Initial)
    val state = _state.asStateFlow()

    override fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Fetch -> fetchUniversities()
        }
    }

    private fun fetchUniversities() = viewModelScope.launch(coroutinesContextProvider.io) {
        getUniversitiesUseCase(Unit)
            .onStart {
                _state.updateState {
                    copy(
                        loading = true,
                        error = null
                    )
                }
            }
            .catch {
                _state.updateState {
                    copy(
                        loading = false,
                        error = exceptionToPresentationMapper(it)
                    )
                }
            }
            .collect { items ->
                _state.updateState {
                    copy(
                        loading = false,
                        data = universitiesToPresentationMapper(items)
                    )
                }
            }
    }
}
