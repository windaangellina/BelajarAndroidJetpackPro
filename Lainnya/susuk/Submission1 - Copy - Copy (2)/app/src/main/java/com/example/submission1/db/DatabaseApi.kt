package com.example.submission1.db

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DatabaseApi {

    @GET("search/multi")
    fun searchFilms(
        @Query("api_key") apiKey:String,
        @Query("query") searchKeyword:String
    ) : Call<ResponseSearchMulti>
}