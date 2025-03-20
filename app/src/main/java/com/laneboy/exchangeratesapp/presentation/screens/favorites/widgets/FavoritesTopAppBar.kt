package com.laneboy.exchangeratesapp.presentation.screens.favorites.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laneboy.exchangeratesapp.R
import com.laneboy.exchangeratesapp.presentation.theme.Header
import com.laneboy.exchangeratesapp.presentation.theme.Outline
import com.laneboy.exchangeratesapp.presentation.theme.text22Bold

@Composable
fun FavoritesTopAppBar(
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .background(color = Header),
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            text = stringResource(R.string.common_favorites),
            style = text22Bold
        )
        HorizontalDivider(color = Outline)
    }
}

@Preview
@Composable
private fun FavoritesTopAppBarPreview() {
    FavoritesTopAppBar()
}
