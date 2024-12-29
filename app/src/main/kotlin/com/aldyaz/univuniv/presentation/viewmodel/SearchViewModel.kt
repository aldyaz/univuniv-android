package com.aldyaz.univuniv.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.aldyaz.univuniv.core.presentation.BaseViewModel
import com.aldyaz.univuniv.domain.interactor.SearchUniversitiesUseCase
import com.aldyaz.univuniv.presentation.intent.SearchIntent
import com.aldyaz.univuniv.presentation.mapper.UniversityToPresentationMapper
import com.aldyaz.univuniv.presentation.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUniversitiesUseCase: SearchUniversitiesUseCase,
    private val universityToPresentationMapper: UniversityToPresentationMapper
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
            .collect { items ->
                _state.updateState {
                    copy(
                        data = List(items.size) {
                            universityToPresentationMapper(items[it])
                        }
                    )
                }
            }
    }
}