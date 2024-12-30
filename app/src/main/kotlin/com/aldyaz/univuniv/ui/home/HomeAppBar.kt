@file:OptIn(ExperimentalMaterial3Api::class)

package com.aldyaz.univuniv.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.aldyaz.univuniv.R

@Composable
fun HomeAppBar(
    onClickSearch: () -> Unit,
    isShowSearchActionButton: Boolean = false
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            AnimatedVisibility(
                visible = isShowSearchActionButton,
                enter = scaleIn(),
                exit = scaleOut()
            ) {
                IconButton(
                    onClick = onClickSearch,
                    content = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "home-search"
                        )
                    }
                )
            }
        }
    )
}