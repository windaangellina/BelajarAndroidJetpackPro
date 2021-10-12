package com.gracielo.jetpacksubmission3v2.Detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity
import com.gracielo.jetpacksubmission3v2.Data.Source.MovieCatalogueRepository

class DetailViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {

    private var itemId = 0
    private var itemType: String? = null
    fun setItemId(itemId: Int) {
        this.itemId = itemId
    }

    fun setItemType(itemType: String?) {
        this.itemType = itemType
    }

    fun getMovieDetail(movieId : Int ):LiveData<MovieEntity> =
        movieCatalogueRepository.getItemMovies(movieId)

    fun getTVDetail(TVId : Int ):LiveData<TVEntity> =
        movieCatalogueRepository.getItemTV(TVId)

    fun setFavoriteMovie(movie: MovieEntity){
        movieCatalogueRepository.setFavoriteMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TVEntity){
        movieCatalogueRepository.setFavoriteTvShow(tvShow)
    }

}