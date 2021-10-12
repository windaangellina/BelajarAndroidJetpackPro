package com.winda.couchpotato.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.winda.couchpotato.data.Show
import com.winda.couchpotato.data.source.MovieDatabaseRepository
import com.winda.couchpotato.data.source.remote.api.response.search.SearchMovieResponse
import com.winda.couchpotato.data.source.remote.api.response.search.SearchTvShowsResponse
import com.winda.couchpotato.utils.api.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowsViewModel(private val repository: MovieDatabaseRepository) : ViewModel() {

    val loadingStatus : LiveData<Boolean> = repository.isLoading
    val responseCode : LiveData<Int> = repository.responseCode

    fun getSearchMovies(searchKeyword: String) : LiveData<Event<SearchMovieResponse>>{
        return repository.getSearchMoviesResult(searchKeyword)
    }

    fun getSearchTvShows(searchKeyword: String) : LiveData<Event<SearchTvShowsResponse>>{
        return repository.getSearchTvShowsResult(searchKeyword)
    }

    fun getListFavorite() : LiveData<PagedList<Show>> {
        return repository.getListFavorite()
    }

    fun getCountFavoriteById(id:Int) : LiveData<Int> {
        return repository.getCountFavoriteById(id)
    }

    fun insertFavorite(favoriteShow : Show) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFavorite(favoriteShow)
        }
    }

    fun deleteFavorite(favoriteShow: Show){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteFavorite(favoriteShow)
        }
    }
}