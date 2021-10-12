package com.winda.couchpotato.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.winda.couchpotato.data.source.MovieDatabaseRepository
import com.winda.couchpotato.repoinjection.Injection

class ViewModelFactory private constructor(private val mMovieDatabaseRepository: MovieDatabaseRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideMovieDatabaseRepository()).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(ShowsViewModel::class.java) -> {
                return ShowsViewModel(mMovieDatabaseRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}