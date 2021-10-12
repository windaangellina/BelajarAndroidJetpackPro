package com.gracielo.jetpacksubmission3v2.Data.Source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity
import com.gracielo.jetpacksubmission3v2.vo.Resource

interface FilmDataSource {
    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getListFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getTvShows(): LiveData<Resource<PagedList<TVEntity>>>
    fun getListFavoriteTvShows(): LiveData<PagedList<TVEntity>>

    fun getItemMovies(id: Int): LiveData<MovieEntity>
    fun getItemTV(id: Int): LiveData<TVEntity>

    fun setFavoriteMovie(movie: MovieEntity)

    fun setFavoriteTvShow(tvShow: TVEntity)
}