package com.winda.couchpotato.data.source.remote.api.response.search


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.winda.couchpotato.data.source.remote.api.RetrofitClient
import kotlinx.android.parcel.Parcelize

@Parcelize
class SearchResultTvShowResponse(
    @SerializedName("backdrop_path")
    var backdropPath: String,
    @SerializedName("first_air_date")
    var firstAirDate: String,
    @SerializedName("genre_ids")
    var genreIds: List<Int>,
    var id: Int,
    var name: String,
    @SerializedName("origin_country")
    var originCountry: List<String>,
    @SerializedName("original_language")
    var originalLanguage: String,
    @SerializedName("original_name")
    var originalName: String,
    var overview: String,
    var popularity: Double,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("vote_average")
    var voteAverage: Double,
    @SerializedName("vote_count")
    var voteCount: Int
) : Parcelable {
    fun getPosterUrl() : String{
        return RetrofitClient.BASE_URL_POSTER + posterPath
    }

    fun getBackdropUrl() : String{
        return RetrofitClient.BASE_URL_BACKDROP + backdropPath
    }
}