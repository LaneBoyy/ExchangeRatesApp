package com.laneboy.exchangeratesapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.laneboy.exchangeratesapp.data.model.FavoriteExchangeDbModel

@Dao
interface FavoriteRatesDao {

    @Query("SELECT * FROM saved_exchange_rates")
    suspend fun getFavoriteRates(): List<FavoriteExchangeDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteExchange(favoriteExchangeDbModel: FavoriteExchangeDbModel)

    @Query("DELETE FROM saved_exchange_rates WHERE baseCurrency=:baseCurrency AND quotedCurrency=:quotedCurrency")
    suspend fun deleteFavoriteExchange(baseCurrency: String, quotedCurrency: String)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavouriteExchange(items: List<FavoriteExchangeDbModel>)
}