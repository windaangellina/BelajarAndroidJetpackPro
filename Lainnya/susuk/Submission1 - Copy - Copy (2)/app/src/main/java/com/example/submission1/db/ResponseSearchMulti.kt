package com.example.submission1.db

import com.example.submission1.codeclass.Result

data class ResponseSearchMulti(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)