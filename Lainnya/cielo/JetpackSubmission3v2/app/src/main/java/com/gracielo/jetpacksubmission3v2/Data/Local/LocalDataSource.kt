package com.gracielo.jetpacksubmission3v2.Data.Local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.Room.FilmDao

class LocalDataSource constructor(private val filmDao: FilmDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao)
    }

    fun getListMovies(): DataSource.Factory<Int,MovieEntity> = filmDao.getListMovies()

    fun getListFavoriteMovies(): DataSource.Factory<Int,MovieEntity> =
        filmDao.getListFavoriteMovies()

    fun getListTvShows(): DataSource.Factory<Int,TVEntity> = filmDao.getListTvShows()

    fun getListFavoriteTvShows(): DataSource.Factory<Int,TVEntity> =
        filmDao.getListFavoriteTvShows()

    fun getDetailMovie(movieId: Int): LiveData<MovieEntity> = filmDao.getDetailMovieById(movieId)

    fun getDetailTvShow(tvShowId: Int): LiveData<TVEntity> =
        filmDao.getDetailTvShowById(tvShowId)

    fun insertMovies(movies: List<MovieEntity>) = filmDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<TVEntity>) = filmDao.insertTvShows(tvShows)

    fun setFavoriteMovie(movie: MovieEntity) {
        movie.isFavorite = !movie.isFavorite
        filmDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TVEntity) {
        tvShow.isFavorite = !tvShow.isFavorite
        filmDao.updateTvShow(tvShow)
    }
}