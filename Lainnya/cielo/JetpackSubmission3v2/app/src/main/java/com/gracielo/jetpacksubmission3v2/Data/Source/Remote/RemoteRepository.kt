package com.gracielo.jetpacksubmission3v2.Data.Source.Remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gracielo.jetpacksubmission3v2.Data.Source.Remote.Response.FilmResponse
import com.gracielo.jetpacksubmission3v2.Data.Source.Remote.Response.MovieResponse
import com.gracielo.jetpacksubmission3v2.Data.Source.Remote.Response.TVResponse
import com.gracielo.jetpacksubmission3v2.Utils.EspressoIdlingResource
import com.gracielo.jetpacksubmission3v2.Utils.JsonHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*

open class RemoteRepository private constructor(jsonHelper: JsonHelper) {
    private val handler = Handler(Looper.getMainLooper())
    private val jsonHelper: JsonHelper = jsonHelper

    companion object {
        private var INSTANCE: RemoteRepository? = null
        fun getInstance(jsonHelper: JsonHelper): RemoteRepository? {
            if (INSTANCE == null) INSTANCE =
                RemoteRepository(jsonHelper)
            return INSTANCE
        }
    }

    fun getMovies():LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val resultFilm = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        handler.postDelayed({
            resultFilm.value = ApiResponse.success(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
        }, 2000)
        return resultFilm
    }
    fun getTV():LiveData<ApiResponse<List<TVResponse>>> {
        EspressoIdlingResource.increment()
        val resultFilm = MutableLiveData<ApiResponse<List<TVResponse>>>()
        handler.postDelayed({
            resultFilm.value = ApiResponse.success(jsonHelper.loadTV())
            EspressoIdlingResource.decrement()
        }, 2000)
        return resultFilm
    }



//    fun getMovies(callback: LoadMoviesCallback) {
//        EspressoIdlingResource.increment()
//        handler.postDelayed({
//            callback.onItemsReceived(jsonHelper.loadMovies() as ArrayList<MovieResponse>)
//            EspressoIdlingResource.decrement()
//        }, 2000)
//    }
//
//    fun getTvShows(callback: LoadTVCallback) {
//        EspressoIdlingResource.increment()
//        handler.postDelayed({
//            callback.onItemsReceived(jsonHelper.loadTV() as ArrayList<TVResponse>)
//            EspressoIdlingResource.decrement()
//        }, 2000)
//    }

    interface LoadMoviesCallback {
        fun onItemsReceived(itemResponses: ArrayList<MovieResponse>)
    }

    interface LoadTVCallback {
        fun onItemsReceived(itemResponses: ArrayList<TVResponse>)
    }
}