package com.gracielo.jetpacksubmission3v2.DataInjection

import android.content.Context
import com.gracielo.jetpacksubmission3v2.Data.Local.LocalDataSource
import com.gracielo.jetpacksubmission3v2.Data.Local.Room.FilmDatabase
import com.gracielo.jetpacksubmission3v2.Data.Source.MovieCatalogueRepository
import com.gracielo.jetpacksubmission3v2.Data.Source.Remote.RemoteRepository
import com.gracielo.jetpacksubmission3v2.Utils.AppExecutors
import com.gracielo.jetpacksubmission3v2.Utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): MovieCatalogueRepository {

        val database = FilmDatabase.getInstance(context)

        val remoteDataSource = RemoteRepository.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.filmDao())
        val appExecutors = AppExecutors()
        return MovieCatalogueRepository.getInstance(remoteDataSource!!,localDataSource,appExecutors)
    }
}