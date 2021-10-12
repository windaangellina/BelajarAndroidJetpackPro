package com.gracielo.jetpacksubmission3v2.Favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity
import com.gracielo.jetpacksubmission3v2.Data.Source.MovieCatalogueRepository

class TVFavourtieViewModel (movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    private val movieCatalogueRepository: MovieCatalogueRepository = movieCatalogueRepository

    fun getFavTV(): LiveData<PagedList<TVEntity>> {
        return movieCatalogueRepository.getListFavoriteTvShows()
    }

}