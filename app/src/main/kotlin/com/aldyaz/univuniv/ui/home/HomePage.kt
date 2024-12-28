package com.aldyaz.univuniv.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aldyaz.univuniv.presentation.intent.HomeIntent
import com.aldyaz.univuniv.presentation.model.UniversityPresentationModel
import com.aldyaz.univuniv.presentation.state.HomeState
import com.aldyaz.univuniv.presentation.viewmodel.HomeViewModel
import com.aldyaz.univuniv.ui.common.FullError
import com.aldyaz.univuniv.ui.common.FullLoading

@Composable
fun HomePage(
    onClickSearch: () -> Unit,
    onClickItem: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScaffold(
        state = state,
        onClickSearch = {},
        onClickItem = {},
        onClickRetry = {
            viewModel.onIntent(HomeIntent.Retry)
        }
    )
}

@Composable
fun HomeScaffold(
    state: HomeState,
    onClickSearch: () -> Unit,
    onClickItem: (String) -> Unit,
    onClickRetry: () -> Unit
) {
    Scaffold(
        topBar = {
            HomeAppBar(
                onClickSearch = onClickSearch
            )
        },
        content = { contentPadding ->
            HomeContent(
                state = state,
                onClickRetry = onClickRetry,
                onClickItem = onClickItem,
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxSize()
            )
        }
    )
}

@Composable
fun HomeContent(
    state: HomeState,
    onClickRetry: () -> Unit,
    onClickItem: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        content = {
            when {
                state.loading -> {
                    FullLoading(
                        modifier = Modifier.fillMaxSize()
                    )
                }

                state.isError -> {
                    FullError(
                        onRetryClick = onClickRetry,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                state.success -> {
                    HomeListContent(
                        universities = state.data,
                        onClickItem = onClickItem
                    )
                }
            }
        }
    )
}

@Composable
fun HomeListContent(
    universities: List<UniversityPresentationModel>,
    onClickItem: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(universities) { item ->
                HomeUniversityItem(
                    item = item,
                    onClick = onClickItem
                )
            }
        }
    )
}
