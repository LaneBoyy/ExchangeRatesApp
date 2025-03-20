package com.laneboy.exchangeratesapp.presentation.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.laneboy.exchangeratesapp.R
import com.laneboy.exchangeratesapp.presentation.common.ExchangeRateItem
import com.laneboy.exchangeratesapp.presentation.common.getApplicationComponent
import com.laneboy.exchangeratesapp.presentation.common.model.ExchangeRate
import com.laneboy.exchangeratesapp.presentation.screens.favorites.widgets.FavoritesTopAppBar
import com.laneboy.exchangeratesapp.presentation.theme.White
import com.laneboy.exchangeratesapp.presentation.theme.text16Medium

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
) {
    val component = getApplicationComponent()
    val viewModel: FavoritesViewModel = viewModel(factory = component.getViewModelFactory())
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getFavoriteRates()
    }

    Column(
        modifier = modifier
            .padding(paddingValues)
            .background(White)
            .fillMaxSize()
    ) {
        FavoritesTopAppBar()
        when (state.screenState) {
            FavoritesViewState.FavoritesScreenState.EMPTY -> {
                FavoritesScreenEmpty(
                    modifier = Modifier.fillMaxSize(),
                )
            }

            FavoritesViewState.FavoritesScreenState.CONTENT -> {
                FavoritesScreenContent(
                    modifier = Modifier.fillMaxSize(),
                    state = state,
                    onMarkerFavoriteClick = { viewModel.deleteFavoriteRate(it) }
                )
            }

            FavoritesViewState.FavoritesScreenState.LOADING -> {}
        }
    }
}

@Composable
private fun FavoritesScreenContent(
    modifier: Modifier = Modifier,
    state: FavoritesViewState,
    onMarkerFavoriteClick: (ExchangeRate) -> Unit,
) {
    val listState = rememberLazyListState()
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        state = listState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(
            items = state.favoriteExchangeRates,
            key = { _, item -> item.quotedCurrency },
        ) { _, rate ->
            ExchangeRateItem(
                exchangeRate = rate,
                showBaseCurrency = true,
                onMarkerFavoriteClick = onMarkerFavoriteClick
            )
        }
    }
}

@Composable
private fun FavoritesScreenEmpty(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Text(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .align(Alignment.Center),
            text = stringResource(R.string.favorite_empty_state),
            style = text16Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoritesScreenPreview() {
    FavoritesScreen(paddingValues = PaddingValues(0.dp))
}
