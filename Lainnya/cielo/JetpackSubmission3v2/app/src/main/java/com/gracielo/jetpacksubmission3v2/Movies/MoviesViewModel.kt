package com.gracielo.jetpacksubmission3v2.Movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity
import com.gracielo.jetpacksubmission3v2.Data.Source.MovieCatalogueRepository
import com.gracielo.jetpacksubmission3v2.vo.Resource
import java.util.*

class MoviesViewModel(movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    private val movieCatalogueRepository: MovieCatalogueRepository

    init {
        this.movieCatalogueRepository = movieCatalogueRepository
    }

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return movieCatalogueRepository.getMovies()
    }

}