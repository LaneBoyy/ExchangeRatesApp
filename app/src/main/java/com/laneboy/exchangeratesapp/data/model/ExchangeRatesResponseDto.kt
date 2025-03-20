package com.laneboy.exchangeratesapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRatesResponseDto(
    @SerialName("base")
    val base: String,
    @SerialName("date")
    val date: String,
    @SerialName("rates")
    val rates: Map<String, Double>
)
