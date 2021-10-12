package com.winda.couchpotato.di

import android.content.Context
import com.winda.couchpotato.data.source.MovieDatabaseRepository
import com.winda.couchpotato.data.source.local.LocalDataSource
import com.winda.couchpotato.data.source.local.roomdb.AppDatabase
import com.winda.couchpotato.data.source.remote.RemoteDataSource
import com.winda.couchpotato.utils.api.JsonHelper

object Injection {
    fun provideMovieDatabaseRepository(context: Context) : MovieDatabaseRepository{
        val database = AppDatabase.getAppDatabase(context)
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val remoteDataSource : RemoteDataSource = RemoteDataSource.getInstance(JsonHelper())

        return MovieDatabaseRepository.getInstance(remoteDataSource, localDataSource)
    }
}