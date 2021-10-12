package com.gracielo.jetpacksubmission3v2.Film

object ApiEndPoint {
    var BASEURL = "http://api.themoviedb.org/3/"
    var APIKEY = "api_key=d41df99ac455e611fbf59daae71c5bf3"

    var NO_ADULT = "&include_adult=false"
    var ONE_PAGE_LIMIT = "&page=1"
    var LANGUAGE = "&language=en-US"

    var SEARCH_MOVIE = "search/movie?"
    var SEARCH_TV = "search/tv?"

    var QUERY = "&query="
    var MOVIE_PLAYNOW = "movie/now_playing?"
    var MOVIE_POPULAR = "discover/movie?"
    var TV_PLAYNOW = "tv/on_the_air?"
    var TV_POPULAR = "discover/tv?"

    var URLIMAGE = "https://image.tmdb.org/t/p/w780/"
    var URLFILM = "https://www.themoviedb.org/movie/"

    var NOTIF_DATE = "&primary_release_date.gte="
    var REALESE_DATE = "&primary_release_date.lte="
    var MOVIE_VIDEO = "movie/{id}/videos?"
    var TV_VIDEO = "tv/{id}/videos?"

}