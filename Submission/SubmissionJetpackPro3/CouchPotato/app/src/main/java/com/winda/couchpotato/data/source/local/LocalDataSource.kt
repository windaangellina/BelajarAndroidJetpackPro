package com.winda.couchpotato.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.winda.couchpotato.data.Show
import com.winda.couchpotato.data.source.local.roomdb.MovieDatabaseDao

open class LocalDataSource(private val movieDatabaseDao: MovieDatabaseDao) {

    companion object {
        @Volatile
        private var instance: LocalDataSource? = null

        fun getInstance(movieDatabaseDao: MovieDatabaseDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(movieDatabaseDao).apply { instance = this }
            }
    }

    fun getListFavorite() : DataSource.Factory<Int, Show> {
       return movieDatabaseDao.getListFavorite()
    }

    fun getCountFavoriteById(id : Int) : LiveData<Int> {
        return movieDatabaseDao.getCountFavoriteById(id)
    }

    suspend fun insertFavorite(show: Show){
        movieDatabaseDao.insertFavorite(show)
    }

    suspend fun deleteFavorite(show: Show){
        movieDatabaseDao.deleteFavorite(show)
    }
}