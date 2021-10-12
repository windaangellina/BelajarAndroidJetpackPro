package com.winda.couchpotato.utils.api

import android.util.Log
import com.winda.couchpotato.data.source.remote.RemoteDataSource
import com.winda.couchpotato.data.source.remote.api.RetrofitClient
import com.winda.couchpotato.data.source.remote.api.response.search.SearchMovieResponse
import com.winda.couchpotato.data.source.remote.api.response.search.SearchTvShowsResponse
import com.winda.couchpotato.utils.test.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JsonHelper {
    companion object{
        private const val TAG = "ShowsViewModel"
        private const val API_KEY = RetrofitClient.API_KEY
    }

    fun loadSearchMovieResult(searchKeyword: String, callback: RemoteDataSource.LoadMoviesCallback){
        RetrofitClient.instanceMovieDatabaseApi.searchMovies(API_KEY, searchKeyword).enqueue(
            object : Callback<SearchMovieResponse> {
                override fun onResponse(
                    call: Call<SearchMovieResponse>,
                    response: Response<SearchMovieResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { callback.onItemsReceived(it) }
                    } else {
                        callback.onUnsuccessfulResponse(responseCode = response.code())
                        Log.e(TAG, "onNotSuccessful tv show response : ${response.message()}")
                    }
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<SearchMovieResponse>, t: Throwable) {
                    callback.onDataNotAvailable()
                    Log.e(TAG, "onFailure movie response : ${t.message.toString()}")

                    EspressoIdlingResource.decrement()
                }
            }
        )
    }

    fun loadSearchTvShowResults(searchKeyword: String, callback: RemoteDataSource.LoadTvShowsCallback){
        RetrofitClient.instanceMovieDatabaseApi.searchTvShows(API_KEY, searchKeyword).enqueue(
            object : Callback<SearchTvShowsResponse> {
                override fun onResponse(
                    call: Call<SearchTvShowsResponse>,
                    response: Response<SearchTvShowsResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { callback.onItemsReceived(it) }
                    } else {
                        callback.onUnsuccessfulResponse(responseCode = response.code())
                        Log.e(TAG, "onNotSuccessful tv show response : ${response.message()}")
                    }

                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<SearchTvShowsResponse>, t: Throwable) {
                    callback.onDataNotAvailable()
                    Log.e(TAG, "onFailure tv show response : ${t.message.toString()}")

                    EspressoIdlingResource.decrement()
                }

            }
        )
    }
}