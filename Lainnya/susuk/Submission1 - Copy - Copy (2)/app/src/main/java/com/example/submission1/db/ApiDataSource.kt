package com.example.submission1.db


open class ApiDataSource private constructor(private val jsonHelper: JsonHelper) {
    companion object {
        @Volatile
        private var instance: ApiDataSource? = null
        fun getInstance(helper: JsonHelper): ApiDataSource =
            instance ?: synchronized(this) {
                instance ?: ApiDataSource(helper).apply { instance = this }
            }
    }

    fun getSearchMovieResult(searchKeyword : String, callback : loadFilm) {
        EspressoIdlingResource.increment()
        jsonHelper.loadFilmResult(searchKeyword, callback)
        EspressoIdlingResource.decrement()
    }

    interface loadFilm{
        fun onItemsReceived(responseSearchMulti: ResponseSearchMulti)
        fun onUnsuccessfulResponse(responseCode : Int)
        fun onDataNotAvailable()
    }
}
