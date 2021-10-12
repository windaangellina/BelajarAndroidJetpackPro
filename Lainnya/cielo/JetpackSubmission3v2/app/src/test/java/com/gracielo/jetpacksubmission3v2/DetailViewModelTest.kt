package com.gracielo.jetpacksubmission3v2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity
import com.gracielo.jetpacksubmission3v2.Data.Source.MovieCatalogueRepository
import com.gracielo.jetpacksubmission3v2.Detail.DetailViewModel
import com.gracielo.jetpacksubmission3v2.Utils.DataDummyFilm
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private val dummyMovie = DataDummyFilm.generateMovies()[0]
    private val movieId = dummyMovie.id
    private val dummyTvShow = DataDummyFilm.generateTV()[0]
    private val tvShowId = dummyTvShow.id

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observerMovie: Observer<MovieEntity>

    @Mock
    private lateinit var observerTvShow: Observer<TVEntity>


    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieCatalogueRepository)

    }
    @Test
    fun getMovieDetail() {
        val movieDummy = MutableLiveData<MovieEntity>()
        movieDummy.value = dummyMovie

        Mockito.`when`(movieCatalogueRepository.getItemMovies(movieId)).thenReturn(movieDummy)

        val movieData = viewModel.getMovieDetail(movieId).value

        Assert.assertNotNull(movieData)
        assertEquals(dummyMovie.judul, movieData!!.judul)
        assertEquals(dummyMovie.id, movieData!!.id)
        assertEquals(dummyMovie.photo, movieData!!.photo)
        assertEquals(dummyMovie.desc, movieData!!.desc)
        assertEquals(dummyMovie.rating, movieData!!.rating)
        assertEquals(dummyMovie.tahun, movieData!!.tahun)

        viewModel.getMovieDetail(movieId).observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)

    }

    @Test
    fun getTvShowDetail() {
        val tvDummy = MutableLiveData<TVEntity>()
        tvDummy.value = dummyTvShow

        Mockito.`when`(movieCatalogueRepository.getItemTV(tvShowId)).thenReturn(tvDummy)

        val tvData = viewModel.getTVDetail(tvShowId).value

        Assert.assertNotNull(tvData)
        assertEquals(dummyTvShow.judul, tvData!!.judul)
        assertEquals(dummyTvShow.id, tvData!!.id)
        assertEquals(dummyTvShow.photo, tvData!!.photo)
        assertEquals(dummyTvShow.desc, tvData!!.desc)
        assertEquals(dummyTvShow.rating, tvData!!.rating)
        assertEquals(dummyTvShow.tahun, tvData!!.tahun)

        viewModel.getTVDetail(tvShowId).observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummyTvShow)
    }

}