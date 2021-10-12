package com.lukitor.projectandroidjetpackpro1.`class`

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(var judul: String, var image: Int, var description: String, var rating: String, var genre: String, var type: String):Parcelable