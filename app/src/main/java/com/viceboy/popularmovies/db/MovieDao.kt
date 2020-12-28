package com.viceboy.popularmovies.db

import androidx.room.*
import com.viceboy.popularmovies.network.model.Movie

@Dao
interface MovieDao {

    @Query("Select * from Movies ORDER BY title ASC")
    fun getMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movieList: List<Movie>)

    @Delete
    fun deleteMovie(movie: Movie)
}