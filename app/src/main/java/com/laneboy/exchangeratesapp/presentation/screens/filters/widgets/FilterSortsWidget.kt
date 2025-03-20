package com.laneboy.exchangeratesapp.presentation.screens.filters.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.laneboy.exchangeratesapp.R
import com.laneboy.exchangeratesapp.presentation.common.model.CurrenciesSortType
import com.laneboy.exchangeratesapp.presentation.theme.Primary
import com.laneboy.exchangeratesapp.presentation.theme.Secondary
import com.laneboy.exchangeratesapp.presentation.theme.text16Medium

@Composable
fun FilterSortsWidget(
    modifier: Modifier = Modifier,
    selectedSortType: CurrenciesSortType,
    onSelectClick: (CurrenciesSortType) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        CurrenciesSortType.entries.forEach { sortType ->
            SortItem(
                currenciesSortType = sortType,
                isSelected = sortType == selectedSortType,
                onClick = onSelectClick
            )
        }
    }
}

@Composable
private fun SortItem(
    modifier: Modifier = Modifier,
    currenciesSortType: CurrenciesSortType,
    isSelected: Boolean,
    onClick: (CurrenciesSortType) -> Unit
) {
    Row(
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = getCurrenciesSortTypeString(currenciesSortType),
            style = text16Medium
        )
        RadioButton(
            modifier = Modifier
                .padding(end = 14.dp)
                .size(20.dp),
            selected = isSelected,
            colors = RadioButtonDefaults.colors(
                selectedColor = Primary,
                unselectedColor = Secondary
            ),
            onClick = { onClick(currenciesSortType) }
        )
    }
}

@Composable
private fun getCurrenciesSortTypeString(currenciesSortType: CurrenciesSortType): String {
    return when (currenciesSortType) {
        CurrenciesSortType.CODE_AZ -> {
            stringResource(R.string.filters_code_az)
        }

        CurrenciesSortType.CODE_ZA -> {
            stringResource(R.string.filters_code_za)
        }

        CurrenciesSortType.QUOTE_ASC -> {
            stringResource(R.string.filters_quote_asc)
        }

        CurrenciesSortType.QUOTE_DESC -> {
            stringResource(R.string.filters_quote_desc)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FilterSortsWidgetPreview() {
    FilterSortsWidget(
        selectedSortType = CurrenciesSortType.CODE_AZ,
        onSelectClick = {}
    )
}
