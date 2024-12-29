package com.aldyaz.univuniv.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aldyaz.univuniv.R
import com.aldyaz.univuniv.presentation.intent.SearchIntent
import com.aldyaz.univuniv.presentation.model.UniversityPresentationModel
import com.aldyaz.univuniv.presentation.state.SearchState
import com.aldyaz.univuniv.presentation.viewmodel.SearchViewModel
import com.aldyaz.univuniv.ui.home.HomeUniversityItem

@Composable
fun SearchPage(
    onClickItem: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SearchScaffold(
        state = state,
        onQueryChange = {
            viewModel.onIntent(SearchIntent.Fetch(it))
        },
        onClickItem = onClickItem
    )
}

@Composable
fun SearchScaffold(
    state: SearchState,
    onQueryChange: (String) -> Unit,
    onClickItem: (String) -> Unit
) {
    Scaffold(
        topBar = {
            Surface(
                contentColor = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth(),
                content = {
                    Box(
                        modifier = Modifier
                            .padding(
                                vertical = 8.dp,
                                horizontal = 16.dp
                            )
                            .statusBarsPadding()
                            .fillMaxWidth(),
                        content = {
                            var textQuery by remember {
                                mutableStateOf(TextFieldValue())
                            }
                            SearchTextField(
                                value = textQuery,
                                onValueChange = {
                                    textQuery = it
                                    onQueryChange(it.text)
                                },
                                hint = stringResource(R.string.label_search),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    )
                }
            )
        },
        content = { contentPadding ->
            SearchContent(
                universities = state.data,
                onClickItem = onClickItem,
                modifier = Modifier.padding(contentPadding)
            )
        }
    )
}

@Composable
fun SearchContent(
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
