package com.viceboy.popularmovies.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class TypeConverterUtil {

    @TypeConverter
    fun fromString(value: String): List<Int> {
        return object : TypeToken<List<Int>>() {}.type.let {
            Gson().fromJson(value, it)
        }
    }

    @TypeConverter
    fun fromList(value: List<Int>): String {
        return Gson().toJson(value)
    }
}