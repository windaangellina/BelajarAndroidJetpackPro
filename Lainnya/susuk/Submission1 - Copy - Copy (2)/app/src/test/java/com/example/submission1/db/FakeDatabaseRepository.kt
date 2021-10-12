package com.example.submission1.db

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

open class FakeDatabaseRepository(private val apiDataSource: ApiDataSource): MainDataSource {
    override fun getSearch(searchKeyword: String): LiveData<ResponseSearchMulti> {
        val liveData = MutableLiveData<ResponseSearchMulti>()
        apiDataSource.getSearchMovieResult(searchKeyword,object : ApiDataSource.loadFilm{
            override fun onItemsReceived(responseSearchMulti: ResponseSearchMulti) {
                liveData.postValue(responseSearchMulti)
            }

            override fun onUnsuccessfulResponse(responseCode: Int) {
                Log.d("Main Repository",responseCode.toString())
            }

            override fun onDataNotAvailable() {
                Log.d("Main Repository","No data found")
            }
        })
        return liveData
    }

}
