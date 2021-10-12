package com.example.submission1.dummy

import com.example.submission1.codeclass.Result
import com.example.submission1.db.ResponseSearchMulti

object TestData {

    fun setRandomData() : ResponseSearchMulti{
        val listResult = ArrayList<Result>()
        val genreIds = listOf(28, 12, 878)
        val originCountry = listOf("US")
        val result = Result(
            false,
            "/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg",
            "2019-04-24",
            genreIds,
            299534,
            "movie",
            "Avengers: Endgame",
            originCountry,
            "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
            "Avengers: Endgame",
            "Avengers: Endgame",
            "2019-04-24",
            8.3,
            "/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
            "2018-04-25",
            "Avengers: Endgame",
            false,
            8.3,
            21739
        )
        listResult.add(result)
        val searchResponse = ResponseSearchMulti(
            1, listResult,1,1
        )
        return searchResponse
    }
}