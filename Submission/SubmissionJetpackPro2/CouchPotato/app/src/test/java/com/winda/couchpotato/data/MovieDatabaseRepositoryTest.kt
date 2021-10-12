package com.winda.couchpotato.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.winda.couchpotato.data.source.remote.RemoteDataSource
import com.winda.couchpotato.utils.DataDummy
import com.winda.couchpotato.utils.LiveDataUtils
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock


class MovieDatabaseRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieDatabaseRepository = FakeMovieDatabaseRepository(remote)

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
}