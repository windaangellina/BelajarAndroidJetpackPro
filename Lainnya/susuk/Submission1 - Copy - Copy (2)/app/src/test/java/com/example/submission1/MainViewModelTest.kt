package com.example.submission1

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.espresso.IdlingRegistry
import com.example.submission1.db.EspressoIdlingResource
import com.example.submission1.db.MainRepository
import com.example.submission1.db.ResponseSearchMulti
import com.example.submission1.dummy.TestData
import com.example.submission1.viewmodel.MainViewModel
import junit.framework.TestCase
import org.junit.*
import org.junit.runner.RunWith

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    private lateinit var viewModel: MainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private val mainRepository = Mockito.mock(MainRepository::class.java)

    @Mock
    private lateinit var observerMulti : Observer<ResponseSearchMulti>

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
        viewModel = MainViewModel(mainRepository)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun testGetListData() {
        val list = TestData.setRandomData()
        val searchResponse = MutableLiveData<ResponseSearchMulti>()
        searchResponse.postValue(list)
        `when`(mainRepository.getSearch("Avengers: Endgame")).thenReturn(searchResponse)
        val searchMoviesEntity = viewModel.getAllData("Avengers: Endgame").value
        verify(mainRepository).getSearch("Avengers: Endgame")
        Assert.assertNotNull(searchMoviesEntity)
        viewModel.getAllData("Avengers: Endgame").observeForever(observerMulti)
        verify(observerMulti).onChanged(list)
    }
}