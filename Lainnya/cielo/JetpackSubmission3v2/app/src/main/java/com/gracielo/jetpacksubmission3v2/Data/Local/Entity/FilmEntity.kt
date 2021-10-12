package com.gracielo.jetpacksubmission3v2.Data.Local.Entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmEntity(
    var id: Int,
    var judul: String,
    var desc: String,
    var photo: String,
    var kategori: String,
    var tahun: String,
    var rating: Float,
    var genre: String
) : Parcelable {
    companion object {
        const val TYPE_MOVIE = "Movies"
        const val TYPE_TV_SHOW = "TV"
    }
}


