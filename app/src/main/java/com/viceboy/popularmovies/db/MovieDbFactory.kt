package com.viceboy.popularmovies.db

import android.content.Context
import androidx.room.Room

object MovieDbFactory {
    fun dbInstance(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "MovieDb")
            .fallbackToDestructiveMigration()
            .build()
}