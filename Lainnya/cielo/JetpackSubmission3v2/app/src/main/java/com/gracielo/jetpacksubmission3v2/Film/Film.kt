package com.gracielo.jetpacksubmission3v2.Film

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    var judul: String,
    var desc: String,
    var photo: String,
    var kategori: String,
    var tahun: String
) : Parcelable