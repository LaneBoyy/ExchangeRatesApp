package com.laneboy.exchangeratesapp.presentation.screens.filters

import androidx.compose.runtime.Immutable
import com.laneboy.exchangeratesapp.presentation.common.model.CurrenciesSortType

@Immutable
data class FiltersViewState(
    val sortType: CurrenciesSortType = CurrenciesSortType.CODE_AZ
)