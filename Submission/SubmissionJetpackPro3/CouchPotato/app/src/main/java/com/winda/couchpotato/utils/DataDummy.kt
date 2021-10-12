package com.winda.couchpotato.utils

import com.winda.couchpotato.data.Show
import com.winda.couchpotato.data.source.remote.api.RetrofitClient
import com.winda.couchpotato.data.source.remote.api.response.search.SearchMovieResponse
import com.winda.couchpotato.data.source.remote.api.response.search.SearchResultMovieResponse
import com.winda.couchpotato.data.source.remote.api.response.search.SearchResultTvShowResponse
import com.winda.couchpotato.data.source.remote.api.response.search.SearchTvShowsResponse
import com.winda.couchpotato.utils.api.Event
import kotlin.math.roundToInt

object DataDummy {
    private const val API_KEY = RetrofitClient.API_KEY

    // dummy movies
    private val genreMovieIds = listOf(12, 878, 28)
    private val movieResult = SearchResultMovieResponse(
        false,
        "/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg",
        genreMovieIds,
        299534,
        "en",
        "Avengers: Endgame",
        "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
        334.07,
        "/ulzhLuWrPK07P1YkdWQLZnQh1JL.jpg",
        "2019-04-24",
        "Avengers: Endgame",
        false,
        8.3,
        18012
    )

    // dummy tv show
    private val genreTvShowIds = listOf(10759, 35)
    private val tvShowResult = SearchResultTvShowResponse(
        "/sf7NCqyVUNoyjYuwW5oJke1T1lH.jpg",
        "2021-02-20",
        genreTvShowIds,
        117376,
        "Vincenzo",
        listOf("KR"),
        "ko",
        "빈센조",
        "Vincenzo Cassano is an Italian lawyer and Mafia consigliere who moves back to Korea due to a conflict within his organization. He ends up crossing paths with a sharp-tongued lawyer named Cha-young, and the two join forces in using villainous methods to take down villains who cannot be punished by the law.",
        63.444,
        "/dvXJgEDQXhL9Ouot2WkBHpQiHGd.jpg",
        9.0,
        18
    )

    fun generateMoviesDataDummy() : Event<SearchMovieResponse> {
        // populate results
        val listSearchResultResponse = ArrayList<SearchResultMovieResponse>()
        listSearchResultResponse.add(movieResult)

        val searchResponse = SearchMovieResponse(
            1, listSearchResultResponse, 1, 1
        )

        return Event(searchResponse)
    }

    fun generateTvShowsDataDummy() : Event<SearchTvShowsResponse> {
        // populate results
        val listResult = ArrayList<SearchResultTvShowResponse>()
        listResult.add(tvShowResult)

        val searchResponse = SearchTvShowsResponse(
            1, listResult, 1, 1
        )
        return Event(searchResponse)
    }

    fun generateMoviesDataDummyResponse() : SearchMovieResponse{
        // populate results
        val listSearchResultResponse = ArrayList<SearchResultMovieResponse>()
        listSearchResultResponse.add(movieResult)

        return SearchMovieResponse(
            1, listSearchResultResponse,1,1
        )
    }

    fun generateTvShowsDataDummyResponse() : SearchTvShowsResponse{
        // populate results
        val listResult = ArrayList<SearchResultTvShowResponse>()
        listResult.add(tvShowResult)

        return SearchTvShowsResponse(
            1, listResult, 1, 1
        )
    }

    fun generateMoviesDummyArrayList() : ArrayList<Show>{
        // populate results
        val listSearchResultResponse = ArrayList<SearchResultMovieResponse>()
        listSearchResultResponse.add(movieResult)

        val response = SearchMovieResponse(
            1, listSearchResultResponse, 1, 1
        )

        // get list show
        val listShow = ArrayList<Show>()
        for (resultDetail in response.searchResultMovieResponses) {
            val userVote = resultDetail.voteAverage * 10
            var releaseDateAsLong : Long = 0

            kotlin.runCatching {
                releaseDateAsLong = resultDetail.releaseDate?.let { FunctionLibrary.getDateAsLong(it) }!!
            }.getOrNull()


            val show = Show(
                resultDetail.id,
                resultDetail.getPosterUrl(),
                resultDetail.getBackdropUrl(),
                resultDetail.title,
                releaseDateAsLong,
                userVote.roundToInt(),
                resultDetail.overview
            )

            if (show !in listShow) {
                listShow.add(show)
            }
        }

        return listShow
    }

    fun generateTvShowsDummyArrayList() : ArrayList<Show>{
        // populate results
        val listResult = ArrayList<SearchResultTvShowResponse>()
        listResult.add(tvShowResult)

        val response = SearchTvShowsResponse(
            1, listResult, 1, 1
        )

        // get list
        val listShow = ArrayList<Show>()

        for (resultDetail in response.searchResultTvShowResponses) {
            val userVote = resultDetail.voteAverage * 10
            var releaseDateAsLong : Long = 0

            // runCatching -> prevent crash when there is no first_air_date key in json result
            kotlin.runCatching {
                if (resultDetail.firstAirDate != "") {
                    releaseDateAsLong = FunctionLibrary.getDateAsLong(resultDetail.firstAirDate)
                }
            }.getOrNull()

            val show = Show(
                resultDetail.id,
                resultDetail.getPosterUrl(),
                resultDetail.getBackdropUrl(),
                resultDetail.name,
                releaseDateAsLong,
                userVote.roundToInt(),
                resultDetail.overview
            )

            if (show !in listShow) {
                listShow.add(show)
            }
        }

        return listShow
    }

    fun generateMoviesDummyList() : List<Show>{
        // populate results
        val listSearchResultResponse = ArrayList<SearchResultMovieResponse>()
        listSearchResultResponse.add(movieResult)

        val response = SearchMovieResponse(
            1, listSearchResultResponse, 1, 1
        )

        // get list show
        val listShow : List<Show> = emptyList()
        for (resultDetail in response.searchResultMovieResponses) {
            val userVote = resultDetail.voteAverage * 10
            var releaseDateAsLong : Long = 0

            kotlin.runCatching {
                releaseDateAsLong = resultDetail.releaseDate?.let { FunctionLibrary.getDateAsLong(it) }!!
            }.getOrNull()

            val show = Show(
                resultDetail.id,
                resultDetail.getPosterUrl(),
                resultDetail.getBackdropUrl(),
                resultDetail.title,
                releaseDateAsLong,
                userVote.roundToInt(),
                resultDetail.overview
            )

            if (show !in listShow) {
                listShow.toMutableList().add(show)
            }
        }

        return listShow
    }

}