package com.gracielo.jetpacksubmission3v2.Data.Source.Remote.Response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmResponse(
    var id: Int,
    var judul: String,
    var desc: String,
    var photo: String,
    var kategori: String,
    var tahun: String,
    var rating: Float,
    var genre: String
) : Parcelable