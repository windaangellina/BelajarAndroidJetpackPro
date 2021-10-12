package com.gracielo.jetpacksubmission3v2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity
import com.gracielo.jetpacksubmission3v2.Data.Source.MovieCatalogueRepository
import com.gracielo.jetpacksubmission3v2.Favourite.MoviesFavouriteViewModel
import com.gracielo.jetpacksubmission3v2.Movies.MoviesViewModel
import com.gracielo.jetpacksubmission3v2.vo.Resource
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesFavoriteViewModelTest {

    private lateinit var viewModel: MoviesFavouriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository : MovieCatalogueRepository

    @Mock
    private lateinit var observerMovie: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MoviesFavouriteViewModel(movieCatalogueRepository)
    }

    @Test
    fun getListFavoriteMovie() {

        val dummyMovie = moviePagedList
        Mockito.`when`(dummyMovie.size).thenReturn(4)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummyMovie

        Mockito.`when`(movieCatalogueRepository.getListFavoriteMovies()).thenReturn(movie)
        val movieEntity = viewModel.getFavMovies().value
        Mockito.verify(movieCatalogueRepository).getListFavoriteMovies()
        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(4, movieEntity?.size)

        viewModel.getFavMovies().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(dummyMovie)

    }

}
