package com.viceboy.popularmovies.di

import com.viceboy.popularmovies.db.MovieDbFactory
import com.viceboy.popularmovies.network.api.MovieApiFactory
import com.viceboy.popularmovies.repository.IMovieRepository
import com.viceboy.popularmovies.repository.MovieRepository
import com.viceboy.popularmovies.ui.movies.MoviesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
}

val networkModule = module {
    single { MovieApiFactory.getApiMovie() }
    single<IMovieRepository> { MovieRepository(get(), get()) }
}

val databaseModule = module {
    single { MovieDbFactory.dbInstance(androidApplication()).movieDao }
}