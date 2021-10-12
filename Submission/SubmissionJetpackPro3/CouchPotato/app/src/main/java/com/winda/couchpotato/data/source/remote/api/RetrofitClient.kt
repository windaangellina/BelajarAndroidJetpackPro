package com.winda.couchpotato.data.source.remote.api

import com.winda.couchpotato.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = BuildConfig.API_KEY_TMDB
    const val BASE_URL_POSTER = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2"
    const val BASE_URL_BACKDROP = "https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces"

    // by lazy -> singleton pattern. only created once
    private val retrofit by lazy {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val instanceMovieDatabaseApi : MovieDatabaseApi by lazy {
        retrofit.create(MovieDatabaseApi::class.java)
    }

}