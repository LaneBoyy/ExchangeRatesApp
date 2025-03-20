package com.laneboy.exchangeratesapp.presentation.screens.filters.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laneboy.exchangeratesapp.R
import com.laneboy.exchangeratesapp.presentation.theme.Header
import com.laneboy.exchangeratesapp.presentation.theme.Outline
import com.laneboy.exchangeratesapp.presentation.theme.Primary
import com.laneboy.exchangeratesapp.presentation.theme.text22Bold

@Composable
fun FiltersTopAppBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Column(modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(color = Header),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(40.dp),
                onClick = onBackClick
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    tint = Primary,
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = stringResource(R.string.filters_title),
                style = text22Bold
            )
        }
        HorizontalDivider(color = Outline)
    }
}

@Preview
@Composable
private fun FiltersTopAppBarPreview() {
    FiltersTopAppBar {}
}
