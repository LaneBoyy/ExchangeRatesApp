package com.laneboy.exchangeratesapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.laneboy.exchangeratesapp.data.model.FavoriteExchangeDbModel

@Database(entities = [FavoriteExchangeDbModel::class], version = 1, exportSchema = false)
abstract class FavoriteRatesDatabase : RoomDatabase() {

    abstract fun favoriteRatesDao(): FavoriteRatesDao

    companion object {
        private var database: FavoriteRatesDatabase? = null
        private const val DATABASE_NAME = "saved_rates.db"
        private val LOCK = Any()

        fun getInstance(context: Context): FavoriteRatesDatabase {
            synchronized(LOCK) {
                database?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context = context,
                        klass = FavoriteRatesDatabase::class.java,
                        name = DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                database = instance
                return instance
            }
        }
    }
}