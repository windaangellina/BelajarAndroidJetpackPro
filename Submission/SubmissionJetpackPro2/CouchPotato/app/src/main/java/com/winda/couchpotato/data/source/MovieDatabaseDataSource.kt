package com.winda.couchpotato.data.source

import androidx.lifecycle.LiveData
import com.winda.couchpotato.data.source.remote.api.response.search.SearchMovieResponse
import com.winda.couchpotato.data.source.remote.api.response.search.SearchTvShowsResponse
import com.winda.couchpotato.utils.api.Event

interface MovieDatabaseDataSource {
    fun getSearchMoviesResult(searchKeyword : String) : LiveData<Event<SearchMovieResponse>>
    fun getSearchTvShowsResult(searchKeyword: String) : LiveData<Event<SearchTvShowsResponse>>
}