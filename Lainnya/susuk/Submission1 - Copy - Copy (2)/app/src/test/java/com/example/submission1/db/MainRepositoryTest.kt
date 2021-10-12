package com.example.submission1.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.submission1.db.ApiDataSource.loadFilm
import com.example.submission1.db.liveDataUtils.getValue
import com.example.submission1.dummy.TestData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock

class MainRepositoryTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(ApiDataSource::class.java)
    private val databaseRepository = FakeDatabaseRepository(remote)
    private val response = TestData.setRandomData()

    @Test
    fun testGetSearchMoviesResult() {
        assertNotNull(response)

        doAnswer { invocation ->
            (invocation.arguments[1] as loadFilm)
                    .onItemsReceived(response)
            null
        }.`when`(remote).getSearchMovieResult(eq("Avengers: Endgame"), any())

        val liveData = getValue(databaseRepository.getSearch("Avengers: Endgame"))
        verify<ApiDataSource>(remote).getSearchMovieResult(eq("Avengers: Endgame"), any())

        assertNotNull(liveData)


    }
}