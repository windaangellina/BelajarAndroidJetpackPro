package com.winda.submissionjetpackpro1.data

import com.winda.submissionjetpackpro1.utils.DataDummy
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class ShowsViewModelTest : TestCase() {

    private lateinit var showsViewModel: ShowsViewModel

    @Before
    override fun setUp(){
        super.setUp()
        showsViewModel = ShowsViewModel()
    }

    @Test
    fun testGetListShowsLiveData() {
        val showsLiveData = showsViewModel.listShowLiveData
        assertNotNull(showsLiveData)
    }

    @Test
    fun testSetListShowsLiveData() {
        val showsLiveData = showsViewModel.listShowLiveData
        val dummyShows = DataDummy.generateRandomDataDummy()

        showsLiveData.postValue(dummyShows)
    }
}