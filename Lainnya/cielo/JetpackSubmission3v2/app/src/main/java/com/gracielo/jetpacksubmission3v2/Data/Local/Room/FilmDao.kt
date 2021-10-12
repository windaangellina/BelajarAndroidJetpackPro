package com.gracielo.jetpacksubmission3v2.Data.Local.Room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity


@Dao
interface FilmDao {
    @Query("SELECT * FROM MovieFavs")
    fun getListMovies() : DataSource.Factory<Int,MovieEntity>

    @Query("SELECT * FROM TVFavs")
    fun getListTvShows() : DataSource.Factory<Int,TVEntity>

    @Query("SELECT * FROM MovieFavs WHERE isFavorite = 1")
    fun getListFavoriteMovies() : DataSource.Factory<Int,MovieEntity>

    @Query("SELECT * FROM TVFavs WHERE isFavorite = 1")
    fun getListFavoriteTvShows() : DataSource.Factory<Int,TVEntity>

    @Query("SELECT * FROM MovieFavs WHERE movieId = :movieId")
    fun getDetailMovieById(movieId: Int) : LiveData<MovieEntity>

    @Query("SELECT * FROM TVFavs WHERE TVId = :tvShowId")
    fun getDetailTvShowById(tvShowId: Int) : LiveData<TVEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieEntity::class)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TVEntity::class)
    fun insertTvShows(tvShows: List<TVEntity>)

    @Update(entity = MovieEntity::class)
    fun updateMovie(movie : MovieEntity)

    @Update(entity = TVEntity::class)
    fun updateTvShow(tvShows: TVEntity)
}