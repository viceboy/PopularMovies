package com.viceboy.popularmovies.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.viceboy.popularmovies.db.MovieDao
import com.viceboy.popularmovies.network.api.MovieApi
import com.viceboy.popularmovies.utils.TestUtils
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test

class MovieRepositoryTest {

    private val api = mock<MovieApi>()
    private val dao = mock<MovieDao>()

    private lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        movieRepository = MovieRepository(dao, api)
    }

    @Test
    fun testGetMovies() {
        whenever(api.getMovies()).thenReturn(Single.just(TestUtils.mockMovieResponse))
        movieRepository.getMovies()
            .test()
            .assertComplete()
            .assertValue {
                it.movies.contains(TestUtils.mockMovie)
            }
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }
}