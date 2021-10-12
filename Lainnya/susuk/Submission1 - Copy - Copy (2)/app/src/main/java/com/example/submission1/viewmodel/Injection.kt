package com.example.submission1.viewmodel

import com.example.submission1.db.ApiDataSource
import com.example.submission1.db.JsonHelper
import com.example.submission1.db.MainRepository

object Injection {
    fun provideMainRepository() : MainRepository{
        val remoteDataSource : ApiDataSource = ApiDataSource.getInstance(JsonHelper())
        return MainRepository.getInstance(remoteDataSource)
    }
}