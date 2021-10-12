package com.gracielo.jetpacksubmission3v2.Data.Source.Remote.Response

data class MovieResponse(
    var id: Int,
    var judul: String,
    var desc: String,
    var photo: String,
    var kategori: String,
    var tahun: String,
    var rating: Float,
    var genre: String
)