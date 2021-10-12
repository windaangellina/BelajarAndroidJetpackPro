package com.winda.couchpotato.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.winda.couchpotato.data.source.remote.RemoteDataSource
import com.winda.couchpotato.data.source.remote.api.response.search.SearchMovieResponse
import com.winda.couchpotato.data.source.remote.api.response.search.SearchTvShowsResponse
import com.winda.couchpotato.utils.api.Event

open class MovieDatabaseRepository private constructor(private val remoteDataSource: RemoteDataSource) : MovieDatabaseDataSource {
    // status
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _responseCode = MutableLiveData<Int>()
    val responseCode : LiveData<Int> = _responseCode

    companion object {
        private const val TAG = "MovieDatabaseRepository"

        @Volatile
        private var instance: MovieDatabaseRepository? = null

        fun getInstance(remoteData: RemoteDataSource): MovieDatabaseRepository =
                instance ?: synchronized(this) {
                    instance ?: MovieDatabaseRepository(remoteData).apply { instance = this }
                }
    }

    override fun getSearchMoviesResult(searchKeyword: String): LiveData<Event<SearchMovieResponse>> {
        _isLoading.value = true
        val searchResponseMutable = MutableLiveData<Event<SearchMovieResponse>>()

        remoteDataSource.getSearchMovieResult(searchKeyword, object : RemoteDataSource.LoadMoviesCallback{
            override fun onItemsReceived(searchMovieResponse: SearchMovieResponse) {
                _isLoading.value = false
                _responseCode.value = 200 //successful response code

                val searchResponseEvent = Event(searchMovieResponse)
                searchResponseMutable.postValue(searchResponseEvent)
            }

            override fun onUnsuccessfulResponse(responseCode: Int) {
                _responseCode.value = responseCode
            }

            override fun onDataNotAvailable() {
                _isLoading.value = false
                Log.d(TAG, "onDataNotAvailable : search movie results response")
            }
        })

        return searchResponseMutable
    }

    override fun getSearchTvShowsResult(searchKeyword: String): LiveData<Event<SearchTvShowsResponse>> {
        _isLoading.value = true
        val searchResponseMutable = MutableLiveData<Event<SearchTvShowsResponse>>()

        remoteDataSource.getSearchTvShowResult(searchKeyword, object : RemoteDataSource.LoadTvShowsCallback{
            override fun onItemsReceived(searchTvShowsResponse: SearchTvShowsResponse) {
                _isLoading.value = false
                _responseCode.value = 200 //successful response code

                val searchResponseEvent = Event(searchTvShowsResponse)
                 searchResponseMutable.postValue(searchResponseEvent)
            }

            override fun onUnsuccessfulResponse(responseCode: Int) {
                _responseCode.value = responseCode
            }

            override fun onDataNotAvailable() {
                _isLoading.value = false
                Log.d(TAG, "onDataNotAvailable : search tv show results response")
            }
        })

        return searchResponseMutable
    }

}