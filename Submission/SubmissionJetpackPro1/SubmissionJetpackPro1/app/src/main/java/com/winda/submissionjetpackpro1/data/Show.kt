package com.winda.submissionjetpackpro1.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Parcelize
class Show (
        val id : Int,
        val categoryId : Int,
        val posterId : Int,
        val title: String,
        val durationHour : Int,
        val durationMinute : Int,
        private val releaseDate : LocalDate?,
        val ratings : String,
        private val userScores : Int,
        val tagLine : String,
        val linkTrailer : String,
        val overview : String) : Parcelable {

    fun getReleaseDateAsString() : String? {
        val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH)
        return releaseDate?.format(formatter)
    }

    fun getDurationAsString() : String{
        return if (categoryId == 1){
            val h = "h"
            val m = "m"
            "$durationHour$h $durationMinute$m"
        } else{
            "-"
        }
    }

    fun getUserScoresAsString() : String{
        return "$userScores %"
    }

    fun getTitleWithReleaseYear() : String{
        return "$title (${releaseDate?.year})"
    }
}