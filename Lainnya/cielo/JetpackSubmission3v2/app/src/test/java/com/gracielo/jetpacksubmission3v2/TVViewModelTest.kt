package com.gracielo.jetpacksubmission3v2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity
import com.gracielo.jetpacksubmission3v2.Data.Source.MovieCatalogueRepository
import com.gracielo.jetpacksubmission3v2.TV.TVViewModel
import com.gracielo.jetpacksubmission3v2.Utils.DataDummyFilm
import com.gracielo.jetpacksubmission3v2.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TVViewModel

    @Mock
    private val movieCatalogueRepository = Mockito.mock(MovieCatalogueRepository::class.java)

    @Mock
    private lateinit var observerTV: Observer<Resource<PagedList<TVEntity>>>

    @Mock
    private lateinit var tvPagedList: PagedList<TVEntity>

    @Before
    fun setUp() {
        viewModel = TVViewModel(movieCatalogueRepository)
    }

    @Test
    fun getListNowPlayingTV() {
        val dummyTV = Resource.success(tvPagedList)
        Mockito.`when`(dummyTV.data?.size).thenReturn(2)
        val TV = MutableLiveData<Resource<PagedList<TVEntity>>>()
        TV.value = dummyTV
        Mockito.`when`(movieCatalogueRepository.getTvShows()).thenReturn(TV)
        val TVEntity = viewModel.TV.value?.data
        Mockito.verify(movieCatalogueRepository).getTvShows()
        Assert.assertNotNull(TVEntity)
        Assert.assertEquals(2, TVEntity?.size)

        viewModel.TV.observeForever(observerTV)
        Mockito.verify(observerTV).onChanged(dummyTV)
    }
}