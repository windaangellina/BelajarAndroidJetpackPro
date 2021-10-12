package com.winda.couchpotato.repoinjection

import com.winda.couchpotato.data.source.MovieDatabaseRepository
import com.winda.couchpotato.data.source.remote.RemoteDataSource
import com.winda.couchpotato.utils.api.JsonHelper

object Injection {
    fun provideMovieDatabaseRepository() : MovieDatabaseRepository{
        val remoteDataSource : RemoteDataSource = RemoteDataSource.getInstance(JsonHelper())
        return MovieDatabaseRepository.getInstance(remoteDataSource)
    }
}