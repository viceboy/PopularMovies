package com.viceboy.popularmovies.utils

import com.viceboy.popularmovies.network.model.Movie
import com.viceboy.popularmovies.network.model.MovieResponse

object TestUtils {

    const val testId = 141
    const val testTitle = "TestTitle"
    const val testDate = "28-06-2020"

    val mockMovie = Movie(
        adult = false,
        backdrop_path = "empty_backdrop",
        genre_ids = emptyList(),
        id = testId,
        original_language = "en",
        original_title = "originals",
        overview = "overview",
        popularity = 34f,
        poster_path = null,
        release_date = testDate,
        title = testTitle,
        video = false,
        vote_average = 12f,
        vote_count = 12
    )

    val mockMovieResponse = MovieResponse(
        page = 1,
        results = mockMovieList(),
        totalPages = 2,
        totalResults = 4
    )

    fun mockMovieList() : List<Movie> {
        return arrayListOf(mockMovie)
    }
}