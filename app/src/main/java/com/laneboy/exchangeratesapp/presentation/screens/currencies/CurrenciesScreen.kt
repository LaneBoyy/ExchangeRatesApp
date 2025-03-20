package com.laneboy.exchangeratesapp.presentation.screens.currencies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.laneboy.exchangeratesapp.presentation.common.ExchangeRateItem
import com.laneboy.exchangeratesapp.presentation.common.getApplicationComponent
import com.laneboy.exchangeratesapp.presentation.common.model.ExchangeRate
import com.laneboy.exchangeratesapp.presentation.screens.currencies.widgets.CurrenciesTopAppBar
import com.laneboy.exchangeratesapp.presentation.screens.currencies.widgets.ErrorInternetWidget
import com.laneboy.exchangeratesapp.presentation.theme.Primary
import com.laneboy.exchangeratesapp.presentation.theme.White

@Composable
fun CurrenciesScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    sortType: String?,
    onFiltersClick: (String) -> Unit
) {
    val component = getApplicationComponent()
    val viewModel: CurrenciesViewModel = viewModel(factory = component.getViewModelFactory())
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.initScreen(sortTypeValue = sortType)
    }

    Column(modifier = modifier
        .padding(paddingValues)
        .background(White)
        .fillMaxSize()) {
        CurrenciesTopAppBar(
            baseCurrencies = state.baseCurrencies,
            selectedCurrency = state.selectedCurrency,
            onCurrencySelect = { viewModel.setSelectBaseCurrencyAndUpdate(it) },
            onFiltersClick = {
                onFiltersClick(state.selectedSort.name)
            }
        )
        when (state.screenState) {

            CurrenciesViewState.CurrenciesScreenState.CONTENT -> {
                CurrenciesScreenContent(
                    state = state,
                    onMarkerFavoriteClick = { viewModel.addOrDeleteFavoriteRate(it) },
                    onRefresh = { viewModel.updateExchangeRates() }
                )
            }

            CurrenciesViewState.CurrenciesScreenState.LOADING -> {
                ProgressIndicator(
                    modifier = Modifier.fillMaxSize(),
                    size = 48.dp
                )
            }

            CurrenciesViewState.CurrenciesScreenState.ERROR -> {
                ErrorInternetWidget(
                    onButtonClick = { viewModel.updateAfterError() }
                )
            }
        }
    }
}

@Composable
private fun CurrenciesScreenContent(
    modifier: Modifier = Modifier,
    state: CurrenciesViewState,
    onMarkerFavoriteClick: (ExchangeRate) -> Unit,
    onRefresh: () -> Unit
) {
    CurrenciesPullToRefreshBox(
        modifier = modifier.fillMaxSize(),
        isListRefreshing = state.isListRefreshing,
        onRefresh = onRefresh
    ) {
        val listState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            state = listState,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(
                items = state.exchangeRates,
                key = { _, item -> item.quotedCurrency },
            ) { _, rate ->
                ExchangeRateItem(
                    exchangeRate = rate,
                    showBaseCurrency = false,
                    onMarkerFavoriteClick = onMarkerFavoriteClick
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CurrenciesPullToRefreshBox(
    modifier: Modifier = Modifier,
    isListRefreshing: Boolean,
    onRefresh: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    val pullToRefreshState = rememberPullToRefreshState()
    PullToRefreshBox(
        modifier = modifier,
        state = pullToRefreshState,
        isRefreshing = isListRefreshing,
        onRefresh = onRefresh,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isListRefreshing,
                state = pullToRefreshState,
                containerColor = White,
                color = Primary
            )
        },
        content = content
    )
}

@Composable
private fun ProgressIndicator(
    modifier: Modifier = Modifier,
    size: Dp
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            color = Primary,
            modifier = Modifier
                .size(size)
                .align(Alignment.Center),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CurrenciesScreenContentPreview() {
    CurrenciesScreenContent(
        state = currenciesViewStateMock,
        onMarkerFavoriteClick = {},
        onRefresh = {},
    )
}
