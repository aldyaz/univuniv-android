package com.aldyaz.univuniv.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aldyaz.univuniv.R

@Composable
fun FullError(
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(R.string.label_default_error_text))
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onRetryClick
                ) {
                    Text(text = stringResource(R.string.label_try_again))
                }
            }
        }
    )
}