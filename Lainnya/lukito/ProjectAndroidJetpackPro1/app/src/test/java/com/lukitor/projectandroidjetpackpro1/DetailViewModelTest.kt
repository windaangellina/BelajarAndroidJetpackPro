package com.lukitor.projectandroidjetpackpro1

import com.lukitor.projectandroidjetpackpro1.`class`.Data
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailVieModel
    private lateinit var tempjudul: String

    @Before
    fun setUp() {
        viewModel = DetailVieModel()
        tempjudul = Data.judul[0]
    }

    @Test
    fun getData(){
        val dataShow = viewModel.getData(tempjudul)
        Assert.assertNotNull(dataShow)
        assertEquals(tempjudul,dataShow.judul)
    }
}