package com.viceboy.popularmovies.repository

import com.viceboy.popularmovies.db.MovieDao
import com.viceboy.popularmovies.network.api.MovieApi
import com.viceboy.popularmovies.network.model.Movie
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface IMovieRepository {
    fun getMovies(): Single<MovieList>
}

class MovieRepository(val dao: MovieDao, val movieApi: MovieApi) : IMovieRepository {

    private val cachedMovies = mutableListOf<Movie>()

    override fun getMovies(): Single<MovieList> {
        return Maybe.concat(cachedMovies(), roomMovies(), apiMovies())
            .firstOrError()
            .subscribeOn(Schedulers.io())
    }

    private fun apiMovies(): Maybe<MovieList> {
        return movieApi.getMovies().map {
            dao.saveMovies(it.results)
            cachedMovies.clear()
            cachedMovies.addAll(it.results)
            MovieList(it.results)
        }.toMaybe()
    }

    private fun roomMovies(): Maybe<MovieList> {
        return Maybe.create {
            val movies = dao.getMovies()
            if (movies.isNotEmpty()) {
                cachedMovies.clear()
                cachedMovies.addAll(movies)
                it.onSuccess(MovieList(movies))
            }
            it.onComplete()
        }
    }

    private fun cachedMovies(): Maybe<MovieList> {
        return Maybe.create {
            if (cachedMovies.isNotEmpty()) it.onSuccess(MovieList(cachedMovies))
            it.onComplete()
        }
    }
}

class MovieList(val movies: List<Movie>)