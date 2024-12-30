package com.aldyaz.univuniv.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.aldyaz.univuniv.core.presentation.BaseViewModel
import com.aldyaz.univuniv.core.presentation.ExceptionToPresentationMapper
import com.aldyaz.univuniv.domain.interactor.SearchUniversitiesUseCase
import com.aldyaz.univuniv.presentation.intent.SearchIntent
import com.aldyaz.univuniv.presentation.mapper.UniversitiesToPresentationMapper
import com.aldyaz.univuniv.presentation.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUniversitiesUseCase: SearchUniversitiesUseCase,
    private val universitiesToPresentationMapper: UniversitiesToPresentationMapper,
    private val exceptionToPresentationMapper: ExceptionToPresentationMapper
) : BaseViewModel<SearchIntent>() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    override fun onIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.Fetch -> getUniversities(intent.query)
        }
    }

    private fun getUniversities(query: String) = viewModelScope.launch(Dispatchers.IO) {
        searchUniversitiesUseCase(query)
            .onStart {
                _state.updateState {
                    copy(
                        loading = true,
                        error = null
                    )
                }
            }
            .catch { err ->
                _state.updateState {
                    copy(
                        loading = false,
                        error = exceptionToPresentationMapper(err)
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