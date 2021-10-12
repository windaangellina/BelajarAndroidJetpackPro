package com.example.submission1.db

import androidx.lifecycle.LiveData

interface MainDataSource {
    fun getSearch(searchKeyword : String) : LiveData<ResponseSearchMulti>
}