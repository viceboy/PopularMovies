package com.viceboy.popularmovies.ui.state

sealed class Resource<out T> {
    data class Loading<out T>(val loading : Boolean = false) : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure<out T>(val exception: Throwable) : Resource<T>()
}

enum class LoadState {
    LOADING, SUCCESS, FAILURE
}
