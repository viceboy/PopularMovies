package com.viceboy.popularmovies.network.api

import com.viceboy.popularmovies.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.themoviedb.org"
const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w342"

object MovieApiFactory {

    fun getPosterUrl(posterPath: String?) = BASE_POSTER_URL + posterPath

    fun getApiMovie(): MovieApi {
        val interceptor = createInterceptor()
        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MovieApi::class.java)
    }

    private fun createInterceptor() = Interceptor { chain ->
        val request = chain.request()
        val apiUrl = request.url
        val url = apiUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()

        val newRequest = request.newBuilder().url(url).build()
        return@Interceptor chain.proceed(newRequest)
    }

}