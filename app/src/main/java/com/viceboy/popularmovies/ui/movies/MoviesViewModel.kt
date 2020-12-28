package com.viceboy.popularmovies.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.viceboy.popularmovies.network.model.Movie
import com.viceboy.popularmovies.repository.IMovieRepository
import com.viceboy.popularmovies.ui.base.BaseViewModel
import com.viceboy.popularmovies.ui.state.Resource
import io.reactivex.android.schedulers.AndroidSchedulers

class MoviesViewModel(private val repository: IMovieRepository) : BaseViewModel() {

    private val mutableMoviesLiveData = MutableLiveData<Resource<List<Movie>>>()
    val moviesLiveData: LiveData<Resource<List<Movie>>>
        get() = mutableMoviesLiveData

    init {
        loadMovies()
    }

    private fun loadMovies() {
        mutableMoviesLiveData.value = Resource.Loading()
        addDisposable(repository.getMovies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mutableMoviesLiveData.value = Resource.Success(it.movies)
            },{
                mutableMoviesLiveData.value = Resource.Failure(it)
            })
        )
    }
}