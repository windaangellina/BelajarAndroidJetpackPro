package com.gracielo.jetpacksubmission3vs.Data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.FilmEntity
import com.gracielo.jetpacksubmission3v2.Data.FakeMovieCatalogueRepository
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.LocalDataSource
import com.gracielo.jetpacksubmission3v2.Data.Source.MovieCatalogueRepository
import com.gracielo.jetpacksubmission3v2.Data.Source.Remote.RemoteRepository
import com.gracielo.jetpacksubmission3v2.LiveDataTestUtil
import com.gracielo.jetpacksubmission3v2.PagedListUtil
import com.gracielo.jetpacksubmission3v2.Utils.AppExecutors
import com.gracielo.jetpacksubmission3v2.Utils.DataDummyFilm
import com.gracielo.jetpacksubmission3v2.vo.Resource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.invocation.InvocationOnMock


class MovieCatalogueRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteRepository::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val moviesRepository = FakeMovieCatalogueRepository(remote,local,appExecutors)
    private val moviesResponses = DataDummyFilm.generateMovies()
    private val TVResponses = DataDummyFilm.generateTV()
    private val movie = moviesResponses[0]
    private val tv = TVResponses[0]

    @Test
    fun getAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getListMovies()).thenReturn(dataSourceFactory)
        moviesRepository.getMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummyFilm.generateMovies()))
        Mockito.verify(local).getListMovies()
        org.junit.Assert.assertNotNull(movieEntity.data)
        assertEquals(moviesResponses.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getAllTV() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVEntity>
        Mockito.`when`(local.getListTvShows()).thenReturn(dataSourceFactory)
        moviesRepository.getTvShows()

        val tvEntity = Resource.success(PagedListUtil.mockPagedList(DataDummyFilm.generateTV()))
        Mockito.verify(local).getListTvShows()
        org.junit.Assert.assertNotNull(tvEntity.data)
        assertEquals(TVResponses.size.toLong(), tvEntity.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = movie
        Mockito.`when`(local.getDetailMovie(movie.id)).thenReturn(dummyMovie)

        val data = LiveDataTestUtil.getValue(moviesRepository.getItemMovies(movie.id))
        Mockito.verify(local).getDetailMovie(movie.id)
        org.junit.Assert.assertNotNull(data)
        assertEquals(movie.id, data.id)
    }

    @Test
    fun getTVDetail() {
        val dummyMovie = MutableLiveData<TVEntity>()
        dummyMovie.value = tv
        Mockito.`when`(local.getDetailTvShow(tv.id)).thenReturn(dummyMovie)

        val data = LiveDataTestUtil.getValue(moviesRepository.getItemTV(tv.id))
        Mockito.verify(local).getDetailTvShow(tv.id)
        org.junit.Assert.assertNotNull(data)
        assertEquals(tv.id, data.id)
    }

    @Test
    fun getAllMoviesFav() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getListFavoriteMovies()).thenReturn(dataSourceFactory)
        moviesRepository.getListFavoriteMovies()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummyFilm.generateMovies()))
        Mockito.verify(local).getListFavoriteMovies()
        org.junit.Assert.assertNotNull(movieEntity.data)
        assertEquals(moviesResponses.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getAllTVFav() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVEntity>
        Mockito.`when`(local.getListFavoriteTvShows()).thenReturn(dataSourceFactory)
        moviesRepository.getListFavoriteTvShows()

        val tvEntity = Resource.success(PagedListUtil.mockPagedList(DataDummyFilm.generateTV()))
        Mockito.verify(local).getListFavoriteTvShows()
        org.junit.Assert.assertNotNull(tvEntity.data)
        assertEquals(TVResponses.size.toLong(), tvEntity.data?.size?.toLong())
    }
}