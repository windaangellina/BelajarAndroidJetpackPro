package com.winda.couchpotato.data.source.remote

import com.winda.couchpotato.data.source.remote.api.response.search.SearchMovieResponse
import com.winda.couchpotato.data.source.remote.api.response.search.SearchTvShowsResponse
import com.winda.couchpotato.utils.api.JsonHelper
import com.winda.couchpotato.utils.test.EspressoIdlingResource

open class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {
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
        jsonHelper.loadSearchMovieResult(searchKeyword, callback)
    }

    fun getSearchTvShowResult(searchKeyword: String, callback : LoadTvShowsCallback){
        EspressoIdlingResource.increment()
        jsonHelper.loadSearchTvShowResults(searchKeyword, callback)
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