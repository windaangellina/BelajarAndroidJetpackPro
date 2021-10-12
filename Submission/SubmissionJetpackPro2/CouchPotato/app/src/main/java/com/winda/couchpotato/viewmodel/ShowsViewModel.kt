package com.winda.couchpotato.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.winda.couchpotato.data.source.MovieDatabaseRepository
import com.winda.couchpotato.data.source.remote.api.response.search.SearchMovieResponse
import com.winda.couchpotato.data.source.remote.api.response.search.SearchTvShowsResponse
import com.winda.couchpotato.utils.api.Event

class ShowsViewModel(private val repository: MovieDatabaseRepository) : ViewModel() {

    fun getLoadingStatus() : LiveData<Boolean>{
        return repository.isLoading
    }

    fun getResponseCode() : LiveData<Int>{
        return repository.responseCode
    }

    fun getSearchMovies(searchKeyword: String) : LiveData<Event<SearchMovieResponse>>{
        return repository.getSearchMoviesResult(searchKeyword)
    }

    fun getSearchTvShows(searchKeyword: String) : LiveData<Event<SearchTvShowsResponse>>{
        return repository.getSearchTvShowsResult(searchKeyword)
    }
}