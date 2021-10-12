package com.winda.couchpotato.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.espresso.IdlingRegistry
import com.winda.couchpotato.data.source.MovieDatabaseRepository
import com.winda.couchpotato.data.source.remote.api.response.search.SearchMovieResponse
import com.winda.couchpotato.data.source.remote.api.response.search.SearchTvShowsResponse
import com.winda.couchpotato.utils.DataDummy
import com.winda.couchpotato.utils.test.EspressoIdlingResource
import com.winda.couchpotato.utils.api.Event
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ShowsViewModelTest {

    private lateinit var showsViewModel: ShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private val movieDatabaseRepository = Mockito.mock(MovieDatabaseRepository::class.java)

    @Mock
    private lateinit var observerMovieResponse : Observer<Event<SearchMovieResponse>>

    @Mock
    private lateinit var observerTvShowResponse : Observer<Event<SearchTvShowsResponse>>

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
        showsViewModel = ShowsViewModel(movieDatabaseRepository)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun testGetListMoviesLiveData() {
        val dummyMovies = DataDummy.generateMoviesDataDummy()
        val searchResponse = MutableLiveData<Event<SearchMovieResponse>>()
        searchResponse.postValue(dummyMovies)

        `when`(movieDatabaseRepository.getSearchMoviesResult("avengers endgame")).thenReturn(searchResponse)

        val searchMoviesEntity = showsViewModel.getSearchMovies("avengers endgame").value
        verify(movieDatabaseRepository).getSearchMoviesResult("avengers endgame")
        assertNotNull(searchMoviesEntity)

        showsViewModel.getSearchMovies("avengers endgame").observeForever(observerMovieResponse)
        verify(observerMovieResponse).onChanged(dummyMovies)
    }

    @Test
    fun testGetListTvShowsLiveData(){
        val dummyTvShows = DataDummy.generateTvShowsDataDummy()
        val searchResponse = MutableLiveData<Event<SearchTvShowsResponse>>()
        searchResponse.postValue(dummyTvShows)

        `when`(movieDatabaseRepository.getSearchTvShowsResult("vincenzo")).thenReturn(searchResponse)

        val searchTvShowEntity = showsViewModel.getSearchTvShows("vincenzo").value
        verify(movieDatabaseRepository).getSearchTvShowsResult("vincenzo")
        assertNotNull(searchTvShowEntity)

        showsViewModel.getSearchTvShows("vincenzo").observeForever(observerTvShowResponse)
        verify(observerTvShowResponse).onChanged(dummyTvShows)
    }
}