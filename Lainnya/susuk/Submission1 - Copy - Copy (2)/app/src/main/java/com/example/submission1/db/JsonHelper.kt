package com.example.submission1.db

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JsonHelper {
    companion object{
        private const val API_KEY = RetrofitClient.API_KEY
    }

    fun loadFilmResult(searchKeyword: String, callback: ApiDataSource.loadFilm){
        RetrofitClient.instanceMovieDatabaseApi.searchFilms(API_KEY,searchKeyword).enqueue(object :
            Callback<ResponseSearchMulti>{
            override fun onResponse(
                call: Call<ResponseSearchMulti>,
                response: Response<ResponseSearchMulti>
            ) {
                if(response.isSuccessful){
                    response.body()?.let { callback.onItemsReceived(it) }
                }
                else{
                    callback.onUnsuccessfulResponse(response.code())
                }
            }

            override fun onFailure(call: Call<ResponseSearchMulti>, t: Throwable) {
                callback.onDataNotAvailable()
            }

        })
    }

}