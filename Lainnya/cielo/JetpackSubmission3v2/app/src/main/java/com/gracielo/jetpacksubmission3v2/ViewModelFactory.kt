package com.gracielo.jetpacksubmission3v2

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gracielo.jetpacksubmission3v2.Data.Source.MovieCatalogueRepository
import com.gracielo.jetpacksubmission3v2.DataInjection.Injection
import com.gracielo.jetpacksubmission3v2.Detail.DetailViewModel
import com.gracielo.jetpacksubmission3v2.Favourite.MoviesFavouriteViewModel
import com.gracielo.jetpacksubmission3v2.Favourite.TVFavourtieViewModel
import com.gracielo.jetpacksubmission3v2.Movies.MoviesViewModel
import com.gracielo.jetpacksubmission3v2.TV.TVViewModel

class ViewModelFactory private constructor(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(movieCatalogueRepository) as T
        } else if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(movieCatalogueRepository) as T
        } else if (modelClass.isAssignableFrom(TVViewModel::class.java)) {
            return TVViewModel(movieCatalogueRepository) as T
        } else if (modelClass.isAssignableFrom(MoviesFavouriteViewModel::class.java)) {
            return MoviesFavouriteViewModel(movieCatalogueRepository!!) as T
        }
        else if (modelClass.isAssignableFrom(TVFavourtieViewModel::class.java)) {
            return TVFavourtieViewModel(movieCatalogueRepository!!) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class named " + modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            INSTANCE ?: synchronized(this) {
                ViewModelFactory(Injection.provideRepository(context)).apply { INSTANCE = this }
            }
    }
}