package com.aldyaz.univuniv.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.aldyaz.univuniv.core.presentation.BaseViewModel
import com.aldyaz.univuniv.domain.interactor.GetUniversitiesUseCase
import com.aldyaz.univuniv.presentation.intent.HomeIntent
import com.aldyaz.univuniv.presentation.mapper.UniversityToPresentationMapper
import com.aldyaz.univuniv.presentation.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUniversitiesUseCase: GetUniversitiesUseCase,
    universityToPresentationMapper: UniversityToPresentationMapper
) : BaseViewModel<HomeIntent>() {

    private val _state = MutableStateFlow(HomeState.Initial)
    val state = combine(
        _state,
        getUniversities()
    ) { state, universities ->
        var newState = state
        newState = newState.copy(
            data = List(universities.size) {
                universityToPresentationMapper(universities[it])
            }
        )
        newState
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeState.Initial
    )

    override fun onIntent(intent: HomeIntent) {}

    private fun getUniversities() = getUniversitiesUseCase(Unit)
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
                copy()
            }
        }
        .onCompletion {
            _state.updateState {
                copy(loading = false)
            }
        }
}
