package com.laneboy.exchangeratesapp.domain.model

data class ExchangeRateDomain(
    val baseCurrency: String,
    val quotedCurrency: String,
    val cost: Double
)
