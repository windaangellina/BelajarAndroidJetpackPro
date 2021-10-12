package com.winda.couchpotato.data.source.remote.api.response.search


import com.google.gson.annotations.SerializedName
import com.winda.couchpotato.data.source.remote.api.RetrofitClient

class SearchResultMovieResponse(
    var adult: Boolean,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("genre_ids")
    var genreIds: List<Int>,
    var id: Int,
    @SerializedName("original_language")
    var originalLanguage: String,
    @SerializedName("original_title")
    var originalTitle: String,
    var overview: String,
    var popularity: Double,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("release_date")
    var releaseDate: String?,
    var title: String,
    var video: Boolean,
    @SerializedName("vote_average")
    var voteAverage: Double,
    @SerializedName("vote_count")
    var voteCount: Int
) {
    fun getPosterUrl() : String{
        return RetrofitClient.BASE_URL_POSTER + posterPath
    }

    fun getBackdropUrl() : String{
        return RetrofitClient.BASE_URL_BACKDROP + backdropPath
    }
}