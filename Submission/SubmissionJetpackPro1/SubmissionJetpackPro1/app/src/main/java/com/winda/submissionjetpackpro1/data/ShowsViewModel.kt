package com.winda.submissionjetpackpro1.data

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.winda.submissionjetpackpro1.utils.DataDummy

class ShowsViewModel : ViewModel() {
    // list
    var listShowLiveData = MutableLiveData<ArrayList<Show>>()

    fun initResources(context: Context, categoryId : Int, filterByCategory : Boolean){
        val listShow = ArrayList<Show>()

        listShow.addAll(DataDummy.generateDataDummyFromResources(context, categoryId, filterByCategory))
        listShowLiveData.value = listShow
    }
}