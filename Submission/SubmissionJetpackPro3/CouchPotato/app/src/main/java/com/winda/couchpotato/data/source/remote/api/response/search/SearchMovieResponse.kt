package com.winda.couchpotato.data.source.remote.api.response.search

import com.google.gson.annotations.SerializedName

data class SearchMovieResponse(
    var page: Int,
    @SerializedName("results")
    var searchResultMovieResponses: List<SearchResultMovieResponse>,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("total_results")
    var totalResults: Int
)