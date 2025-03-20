package com.laneboy.exchangeratesapp.data.model

import androidx.room.Entity

@Entity(tableName = "saved_exchange_rates", primaryKeys = ["baseCurrency", "quotedCurrency"])
data class FavoriteExchangeDbModel(
    val baseCurrency: String,
    val quotedCurrency: String,
    val cost: Double
)