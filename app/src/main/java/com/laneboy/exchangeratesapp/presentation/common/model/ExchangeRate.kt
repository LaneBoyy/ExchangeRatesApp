package com.laneboy.exchangeratesapp.presentation.common.model

import androidx.compose.runtime.Immutable

@Immutable
data class ExchangeRate(
    val baseCurrency: String = "",
    val quotedCurrency: String = "",
    val cost: Double = 0.0,
    val isFavorite: Boolean = false
)
