package com.lukitor.projectandroidjetpackpro1

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DataViewModelTest {
    private lateinit var viewModel: DataViewModel

    @Before
    fun setUp() {viewModel = DataViewModel()}

    @Test
    fun getAllList() {
        var tipe = "All" // *NOTES* All untuk semua category, Movie untuk category movie, TV Show untuk category tv show
        val dataEntityList = viewModel.getData(tipe)
        Assert.assertNotNull(dataEntityList)
        if (tipe=="All") Assert.assertEquals(20, dataEntityList.size)
        else Assert.assertEquals(10, dataEntityList.size)
    }

    @Test
    fun getMovieList() {
        var tipe = "Movie" // *NOTES* All untuk semua category, Movie untuk category movie, TV Show untuk category tv show
        val dataEntityList = viewModel.getData(tipe)
        Assert.assertNotNull(dataEntityList)
        if (tipe=="All") Assert.assertEquals(20, dataEntityList.size)
        else Assert.assertEquals(10, dataEntityList.size)
    }

    @Test
    fun getTvShowList() {
        var tipe = "TV Show" // *NOTES* All untuk semua category, Movie untuk category movie, TV Show untuk category tv show
        val dataEntityList = viewModel.getData(tipe)
        Assert.assertNotNull(dataEntityList)
        if (tipe=="All") Assert.assertEquals(20, dataEntityList.size)
        else Assert.assertEquals(10, dataEntityList.size)
    }
}