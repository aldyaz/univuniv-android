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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUniversitiesUseCase: SearchUniversitiesUseCase,
    private val universityToPresentationMapper: UniversityToPresentationMapper
) : BaseViewModel<SearchIntent>() {

    private val _searchQuery = MutableStateFlow("")

    private val _state = MutableStateFlow(SearchState())
    val state = combine(
        _state,
        fetchSearch()
    ) { state, items ->
        var newState = state
        newState = newState.copy(
            data = List(items.size) {
                universityToPresentationMapper(items[it])
            }
        )
        newState
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

    private fun fetchSearch() = _searchQuery
        .debounce(300)
        .flatMapLatest {
            if (it.isNotEmpty() || it.length > 1) searchUniversitiesUseCase(it) else flowOf(
                emptyList()
            )
        }
}