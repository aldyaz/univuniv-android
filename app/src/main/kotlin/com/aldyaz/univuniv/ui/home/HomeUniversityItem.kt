package com.aldyaz.univuniv.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aldyaz.univuniv.presentation.model.UniversityPresentationModel

@Composable
fun HomeUniversityItem(
    item: UniversityPresentationModel,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = {
            item.webPages.firstOrNull()?.let(onClick)
        },
        modifier = modifier,
        content = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                content = {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                    Text(
                        text = item.country,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.padding(vertical = 6.dp))
                    item.webPages.firstOrNull()?.let {
                        Text(text = it)
                    }
                }
            )
        }
    )
}

@Preview
@Composable
fun HomeUniversityItemPreview(modifier: Modifier = Modifier) {
    HomeUniversityItem(
        item = UniversityPresentationModel(
            name = "Universitas Indonesia",
            country = "Indonesia",
            webPages = listOf("http://www.ui.ac.id/")
        ),
        onClick = {},
        modifier = Modifier.fillMaxWidth()
    )
}
