package com.winda.couchpotato.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.winda.couchpotato.data.source.local.LocalDataSource
import com.winda.couchpotato.data.source.remote.RemoteDataSource
import com.winda.couchpotato.utils.DataDummy
import com.winda.couchpotato.utils.LiveDataUtils
import com.winda.couchpotato.utils.PagedListUtils
import com.winda.couchpotato.utils.Resource
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDatabaseRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val movieDatabaseRepository = FakeMovieDatabaseRepository(remote, local)

    // data dummy responses
    private val movieResponse = DataDummy.generateMoviesDataDummyResponse()
    private val tvResponse = DataDummy.generateTvShowsDataDummyResponse()

    @Test
    fun testGetSearchMoviesResult() {
        assertNotNull(movieResponse)

        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMoviesCallback)
                    .onItemsReceived(movieResponse)
            null
        }.`when`(remote).getSearchMovieResult(eq("avengers"), any())

        val movieLiveData = LiveDataUtils.getValue(movieDatabaseRepository.getSearchMoviesResult("avengers"))
        verify<RemoteDataSource>(remote).getSearchMovieResult(eq("avengers"), any())

        Assert.assertNotNull(movieLiveData)
    }

    @Test
    fun testGetSearchTvShowsResult() {
        assertNotNull(tvResponse)

        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvShowsCallback)
                .onItemsReceived(tvResponse)
            null
        }.`when`(remote).getSearchTvShowResult(eq("vincenzo"), any())

        val tvLiveData = LiveDataUtils.getValue(movieDatabaseRepository.getSearchTvShowsResult("vincenzo"))
        verify<RemoteDataSource>(remote).getSearchTvShowResult(eq("vincenzo"),any())

        Assert.assertNotNull(tvLiveData)
    }

    @Test
    fun testGetListFavorite(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Show>
        `when`(local.getListFavorite()).thenReturn(dataSourceFactory)
        movieDatabaseRepository.getListFavorite()

        val movieEntity = Resource.success(PagedListUtils.mockPagedList(DataDummy.generateMoviesDummyList()))
        Mockito.verify(local).getListFavorite()
        Assert.assertNotNull(movieEntity.data)
    }
}