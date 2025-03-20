package com.laneboy.exchangeratesapp.presentation.screens.filters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.laneboy.exchangeratesapp.R
import com.laneboy.exchangeratesapp.presentation.common.getApplicationComponent
import com.laneboy.exchangeratesapp.presentation.common.model.CurrenciesSortType
import com.laneboy.exchangeratesapp.presentation.screens.filters.widgets.FilterSortsWidget
import com.laneboy.exchangeratesapp.presentation.screens.filters.widgets.FiltersApplyButton
import com.laneboy.exchangeratesapp.presentation.screens.filters.widgets.FiltersTopAppBar
import com.laneboy.exchangeratesapp.presentation.theme.White
import com.laneboy.exchangeratesapp.presentation.theme.text12Bold

@Composable
fun FiltersScreen(
    currenciesSortTypeName: String?,
    onNavigationBack: (String?) -> Unit
) {

    val component = getApplicationComponent()
    val viewModel: FiltersViewModel = viewModel(factory = component.getViewModelFactory())
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        val currenciesSortType = getCurrenciesSortType(currenciesSortTypeName)
        viewModel.setCurrenciesSortType(currenciesSortType)
    }

    Scaffold(
        floatingActionButton = {
            FiltersApplyButton(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                onClick = { onNavigationBack(state.sortType.name) }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { scaffoldPaddings ->
        Column(
            modifier = Modifier
                .padding(top = scaffoldPaddings.calculateTopPadding())
                .background(White)
                .fillMaxSize()
        ) {
            FiltersTopAppBar(onBackClick = { onNavigationBack(null) })
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
                text = stringResource(R.string.filters_sort_by).uppercase(),
                style = text12Bold
            )
            FilterSortsWidget(
                modifier = Modifier.padding(start = 16.dp, top = 12.dp, end = 16.dp),
                selectedSortType = state.sortType,
                onSelectClick = { viewModel.setCurrenciesSortType(it) }
            )
        }
    }

}

private fun getCurrenciesSortType(name: String?): CurrenciesSortType {
    return CurrenciesSortType.entries.find { it.name == name } ?: CurrenciesSortType.CODE_AZ
}

@Preview
@Composable
private fun FiltersScreenPreview() {
    FiltersScreen(
        currenciesSortTypeName = CurrenciesSortType.CODE_AZ.name,
        onNavigationBack = {}
    )
}
