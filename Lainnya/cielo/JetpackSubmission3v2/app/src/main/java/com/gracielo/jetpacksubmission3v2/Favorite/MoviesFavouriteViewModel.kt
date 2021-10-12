package com.gracielo.jetpacksubmission3v2.Favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity
import com.gracielo.jetpacksubmission3v2.Data.Source.MovieCatalogueRepository

class MoviesFavouriteViewModel(movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    private val movieCatalogueRepository: MovieCatalogueRepository = movieCatalogueRepository

    fun getFavMovies(): LiveData<PagedList<MovieEntity>> {
        return movieCatalogueRepository.getListFavoriteMovies()
    }

}