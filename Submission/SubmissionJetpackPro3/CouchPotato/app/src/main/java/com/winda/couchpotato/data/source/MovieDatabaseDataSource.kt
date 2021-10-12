package com.winda.couchpotato.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.winda.couchpotato.data.Show
import com.winda.couchpotato.data.source.remote.api.response.search.SearchMovieResponse
import com.winda.couchpotato.data.source.remote.api.response.search.SearchTvShowsResponse
import com.winda.couchpotato.utils.api.Event

interface MovieDatabaseDataSource {
    // API
    fun getSearchMoviesResult(searchKeyword : String) : LiveData<Event<SearchMovieResponse>>
    fun getSearchTvShowsResult(searchKeyword: String) : LiveData<Event<SearchTvShowsResponse>>

    // local
    fun getListFavorite() : LiveData<PagedList<Show>>
//    fun getCountFavoriteById(id:Int) : LiveData<Int>
//    suspend fun insertFavorite(show: Show)
//    suspend fun deleteFavorite(show: Show)
}