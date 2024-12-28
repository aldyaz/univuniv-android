package com.aldyaz.univuniv.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.aldyaz.univuniv.core.presentation.BaseViewModel
import com.aldyaz.univuniv.core.presentation.ExceptionToPresentationMapper
import com.aldyaz.univuniv.domain.interactor.GetUniversitiesUseCase
import com.aldyaz.univuniv.presentation.intent.HomeIntent
import com.aldyaz.univuniv.presentation.mapper.UniversityToPresentationMapper
import com.aldyaz.univuniv.presentation.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUniversitiesUseCase: GetUniversitiesUseCase,
    private val universityToPresentationMapper: UniversityToPresentationMapper,
    private val exceptionToPresentationMapper: ExceptionToPresentationMapper
) : BaseViewModel<HomeIntent>() {

    private val _state = MutableStateFlow(HomeState.Initial)
    val state = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeState.Initial
    )

    init {
        onIntent(HomeIntent.Fetch)
    }

    override fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Fetch -> fetchUniversities()
        }
    }

    private fun fetchUniversities() = viewModelScope.launch(Dispatchers.IO) {
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
                        data = List(items.size) {
                            universityToPresentationMapper(items[it])
                        }
                    )
                }
            }
    }
}
