package com.example.submission1.viewmodel

import android.content.res.TypedArray
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission1.codeclass.Film
import com.example.submission1.db.MainRepository
import com.example.submission1.db.ResponseSearchMulti

class MainViewModel(val repository: MainRepository) : ViewModel() {

    fun getAllData(searchKeyword : String) : LiveData<ResponseSearchMulti>{
        return repository.getSearch(searchKeyword)
    }
}