package com.winda.couchpotato.data.source.remote.api

import com.winda.couchpotato.data.source.remote.api.response.search.SearchMovieResponse
import com.winda.couchpotato.data.source.remote.api.response.search.SearchTvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDatabaseApi {

    // https://api.themoviedb.org/3/search/movie?api_key=0e0818b611e7dfb71928a75cd059f740&query=avengers
    @GET("search/movie")
    fun searchMovies(
        @Query("api_key") apiKey:String,
        @Query("query") searchKeyword:String
    ) : Call<SearchMovieResponse>


    // https://api.themoviedb.org/3/search/tv?api_key=0e0818b611e7dfb71928a75cd059f740&query=walking dead
    @GET("search/tv")
    fun searchTvShows(
        @Query("api_key") apiKey:String,
        @Query("query") searchKeyword:String
    ) : Call<SearchTvShowsResponse>
}