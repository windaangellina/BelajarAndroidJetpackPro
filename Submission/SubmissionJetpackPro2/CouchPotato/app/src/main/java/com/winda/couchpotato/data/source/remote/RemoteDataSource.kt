package com.winda.couchpotato.data.source.remote

import android.os.Handler
import android.os.Looper
import com.winda.couchpotato.data.source.remote.api.response.search.SearchMovieResponse
import com.winda.couchpotato.data.source.remote.api.response.search.SearchTvShowsResponse
import com.winda.couchpotato.utils.test.EspressoIdlingResource
import com.winda.couchpotato.utils.api.JsonHelper

private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

open class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {
    private val handler = Handler(Looper.getMainLooper())

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource(helper).apply { instance = this }
                }
    }

    fun getSearchMovieResult(searchKeyword : String, callback : LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            jsonHelper.loadSearchMovieResult(searchKeyword, callback)
            // EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getSearchTvShowResult(searchKeyword: String, callback : LoadTvShowsCallback){
        EspressoIdlingResource.increment()
        handler.postDelayed({
            jsonHelper.loadSearchTvShowResults(searchKeyword, callback)
            // EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadTvShowsCallback {
        fun onItemsReceived(searchTvShowsResponse: SearchTvShowsResponse)
        fun onUnsuccessfulResponse(responseCode : Int)
        fun onDataNotAvailable()
    }

    interface LoadMoviesCallback {
        fun onItemsReceived(searchMovieResponse: SearchMovieResponse)
        fun onUnsuccessfulResponse(responseCode : Int)
        fun onDataNotAvailable()
    }
}