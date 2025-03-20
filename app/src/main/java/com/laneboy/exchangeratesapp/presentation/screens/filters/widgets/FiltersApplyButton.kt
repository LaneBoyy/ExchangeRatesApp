package com.laneboy.exchangeratesapp.presentation.screens.filters.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laneboy.exchangeratesapp.R
import com.laneboy.exchangeratesapp.presentation.theme.Primary
import com.laneboy.exchangeratesapp.presentation.theme.White
import com.laneboy.exchangeratesapp.presentation.theme.text14Medium

@Composable
fun FiltersApplyButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .height(40.dp)
            .fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(100.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Primary)
    ) {
        Text(
            text = stringResource(R.string.filters_apply),
            style = text14Medium.copy(color = White)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FiltersApplyButtonPreview() {
    FiltersApplyButton {}
}
