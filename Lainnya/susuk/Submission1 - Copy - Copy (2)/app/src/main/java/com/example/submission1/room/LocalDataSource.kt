package com.example.submission1.room

import androidx.lifecycle.LiveData
import com.example.submission1.codeclass.Film

class LocalDataSource private constructor(private val dbDao: DBDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(dbDao: DBDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(dbDao)
    }

    fun getAll(): LiveData<List<Film>> = dbDao.getFavorite()

    suspend fun insertFavorite(favoriteEntity: Film){
        dbDao.insertFavorite(favoriteEntity)
    }

    suspend fun deleteFavorite(favoriteEntity: Film){
        dbDao.deleteFavorite(favoriteEntity)
    }
}