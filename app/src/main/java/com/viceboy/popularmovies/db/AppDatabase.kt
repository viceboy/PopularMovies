package com.viceboy.popularmovies.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.viceboy.popularmovies.network.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(value = [TypeConverterUtil::class])
abstract class AppDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}