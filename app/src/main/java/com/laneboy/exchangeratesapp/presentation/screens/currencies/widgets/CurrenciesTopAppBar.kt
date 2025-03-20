package com.laneboy.exchangeratesapp.presentation.screens.currencies.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laneboy.exchangeratesapp.R
import com.laneboy.exchangeratesapp.domain.model.BaseCurrency
import com.laneboy.exchangeratesapp.presentation.theme.Header
import com.laneboy.exchangeratesapp.presentation.theme.Outline
import com.laneboy.exchangeratesapp.presentation.theme.Primary
import com.laneboy.exchangeratesapp.presentation.theme.Secondary
import com.laneboy.exchangeratesapp.presentation.theme.White
import com.laneboy.exchangeratesapp.presentation.theme.text22Bold

@Composable
fun CurrenciesTopAppBar(
    modifier: Modifier = Modifier,
    baseCurrencies: List<BaseCurrency>,
    selectedCurrency: BaseCurrency,
    onCurrencySelect: (BaseCurrency) -> Unit,
    onFiltersClick: () -> Unit
) {
    Column(
        modifier
            .fillMaxWidth()
            .background(color = Header),
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 10.dp),
            text = stringResource(R.string.common_currencies),
            style = text22Bold
        )
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 18.dp, end = 16.dp)
                .fillMaxWidth()
                .height(48.dp)
        ) {
            FastCurrencySortWidget(
                modifier = Modifier.weight(1f),
                selectedCurrency = selectedCurrency,
                currencies = baseCurrencies,
                onCurrencySelect = onCurrencySelect
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(White)
                    .border(
                        width = 1.dp,
                        color = Secondary,
                        shape = RoundedCornerShape(8.dp)
                    ),
                onClick = onFiltersClick
            ) {
                Icon(
                    modifier = Modifier.size(17.dp),
                    painter = painterResource(R.drawable.ic_filter),
                    tint = Primary,
                    contentDescription = null
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier.padding(top = 12.dp),
            color = Outline
        )
    }
}

@Preview
@Composable
private fun CurrenciesTopAppBarPreview() {
    CurrenciesTopAppBar(
        baseCurrencies = listOf(),
        selectedCurrency = BaseCurrency.EUR,
        onCurrencySelect = {},
        onFiltersClick = {}
    )
}
