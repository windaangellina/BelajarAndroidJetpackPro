package com.gracielo.jetpacksubmission3v2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity
import com.gracielo.jetpacksubmission3v2.Data.Source.MovieCatalogueRepository
import com.gracielo.jetpacksubmission3v2.Favourite.MoviesFavouriteViewModel
import com.gracielo.jetpacksubmission3v2.Favourite.TVFavourtieViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class TVFavouriteViewModelTest {
    private lateinit var viewModel: TVFavourtieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var movieCatalogueRepository : MovieCatalogueRepository

    @Mock
    private lateinit var observerTV: Observer<PagedList<TVEntity>>

    @Mock
    private lateinit var TVPagedList: PagedList<TVEntity>

    @Before
    fun setUp() {
        viewModel = TVFavourtieViewModel(movieCatalogueRepository)
    }

    @Test
    fun getListFavoriteMovie() {

        val dummyTV = TVPagedList
        Mockito.`when`(dummyTV.size).thenReturn(2)
        val TV = MutableLiveData<PagedList<TVEntity>>()
        TV.value = dummyTV

        Mockito.`when`(movieCatalogueRepository.getListFavoriteTvShows()).thenReturn(TV)
        val movieEntity = viewModel.getFavTV().value
        Mockito.verify(movieCatalogueRepository).getListFavoriteTvShows()
        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(2, movieEntity?.size)

        viewModel.getFavTV().observeForever(observerTV)
        Mockito.verify(observerTV).onChanged(dummyTV)

    }
}