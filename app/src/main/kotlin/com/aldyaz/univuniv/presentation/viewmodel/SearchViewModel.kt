package com.aldyaz.univuniv.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.aldyaz.univuniv.core.presentation.BaseViewModel
import com.aldyaz.univuniv.domain.interactor.SearchUniversitiesUseCase
import com.aldyaz.univuniv.presentation.intent.SearchIntent
import com.aldyaz.univuniv.presentation.mapper.UniversityToPresentationMapper
import com.aldyaz.univuniv.presentation.state.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUniversitiesUseCase: SearchUniversitiesUseCase,
    private val universityToPresentationMapper: UniversityToPresentationMapper
) : BaseViewModel<SearchIntent>() {

    private val _searchQuery = MutableStateFlow("")

    private val _state = MutableStateFlow(SearchState())
    val state = _searchQuery
        .debounce(300)
        .flatMapLatest {
            searchUniversitiesUseCase(it)
        }.map { items ->
            _state.updateState {
                copy(
                    data = List(items.size) {
                        universityToPresentationMapper(items[it])
                    }
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SearchState()
        )

    override fun onIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.Fetch -> {
                _searchQuery.value = intent.query
            }
        }
    }
}