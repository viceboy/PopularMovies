package com.viceboy.popularmovies.network.api

import com.viceboy.popularmovies.network.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MovieApi {

    @GET("/3/movie/popular")
    fun getMovies() : Single<MovieResponse>
}