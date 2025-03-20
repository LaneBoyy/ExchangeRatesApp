package com.laneboy.exchangeratesapp.presentation.screens.currencies.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laneboy.exchangeratesapp.R
import com.laneboy.exchangeratesapp.presentation.theme.Primary
import com.laneboy.exchangeratesapp.presentation.theme.White
import com.laneboy.exchangeratesapp.presentation.theme.text14Medium
import com.laneboy.exchangeratesapp.presentation.theme.text16Medium

@Composable
fun ErrorInternetWidget(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.error_no_internet_connection),
                style = text16Medium
            )
        }
        Button(
            modifier = modifier
                .padding(16.dp)
                .height(40.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            onClick = onButtonClick,
            shape = RoundedCornerShape(100.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary)
        ) {
            Text(
                text = stringResource(R.string.error_no_internet_connection_try_again),
                style = text14Medium.copy(color = White)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorInternetWidgetPreview() {
    ErrorInternetWidget {}
}