package com.gracielo.jetpacksubmission3v2.TV

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity
import com.gracielo.jetpacksubmission3v2.Data.Source.MovieCatalogueRepository
import com.gracielo.jetpacksubmission3v2.vo.Resource
import java.util.*

class TVViewModel(movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    private val movieCatalogueRepository: MovieCatalogueRepository

    init {
        this.movieCatalogueRepository = movieCatalogueRepository
    }

    val TV: LiveData<Resource<PagedList<TVEntity>>>
        get() = movieCatalogueRepository.getTvShows()
}
