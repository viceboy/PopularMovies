package com.viceboy.popularmovies.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.viceboy.popularmovies.db.MovieDao
import com.viceboy.popularmovies.network.api.MovieApi
import com.viceboy.popularmovies.network.model.Movie
import com.viceboy.popularmovies.repository.MovieRepository
import com.viceboy.popularmovies.ui.movies.MoviesViewModel
import com.viceboy.popularmovies.ui.state.Resource
import com.viceboy.popularmovies.utils.TestUtils
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieViewModelTest {
    private val api = mock<MovieApi>()
    private val dao = mock<MovieDao>()

    private val dataObserver = mock<Observer<Resource<List<Movie>>>>()

    private lateinit var movieRepository: MovieRepository
    private lateinit var moviesViewModel: MoviesViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        movieRepository = MovieRepository(dao, api)
    }

    @Test
    fun testLoadData() {
        whenever(api.getMovies()).thenReturn(Single.just(TestUtils.mockMovieResponse))
        moviesViewModel = MoviesViewModel(movieRepository)
        moviesViewModel.moviesLiveData.observeForever(dataObserver)

        verify(dataObserver, atLeastOnce()).onChanged(Resource.Loading())
        verify(dataObserver, atLeastOnce()).onChanged(Resource.Success(TestUtils.mockMovieList()))
    }

    @Test
    fun testOnErrorLoadData() {
        val throwable = Throwable("Error!!")
        whenever(api.getMovies()).thenReturn(Single.error(throwable))
        moviesViewModel = MoviesViewModel(movieRepository)
        moviesViewModel.moviesLiveData.observeForever(dataObserver)

        verify(dataObserver, atLeastOnce()).onChanged(Resource.Loading())
        verify(dataObserver, atLeastOnce()).onChanged(Resource.Failure(throwable))
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
        moviesViewModel.moviesLiveData.removeObserver(dataObserver)
    }
}