package com.winda.couchpotato.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.winda.couchpotato.data.source.MovieDatabaseDataSource
import com.winda.couchpotato.data.source.local.LocalDataSource
import com.winda.couchpotato.data.source.remote.RemoteDataSource
import com.winda.couchpotato.data.source.remote.api.response.search.SearchMovieResponse
import com.winda.couchpotato.data.source.remote.api.response.search.SearchTvShowsResponse
import com.winda.couchpotato.utils.api.Event

open class FakeMovieDatabaseRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource : LocalDataSource)
    : MovieDatabaseDataSource {

    // status
    private val _isLoading = MutableLiveData<Boolean>()

    private val _responseCode = MutableLiveData<Int>()

    companion object {
        private const val TAG = "MovieDatabaseRepository"
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

    override fun getListFavorite() : LiveData<PagedList<Show>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getListFavorite(), config).build()
    }

    fun getCountFavoriteById(id: Int): LiveData<Int> {
        return localDataSource.getCountFavoriteById(id)
    }

    suspend fun insertFavorite(show: Show) {
        localDataSource.insertFavorite(show)
    }

    suspend fun deleteFavorite(show: Show) {
        localDataSource.deleteFavorite(show)
    }
}