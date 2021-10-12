package com.winda.couchpotato.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Parcelize
class Show (
        val id : Int,
        val posterUrl : String?,
        val backdropUrl : String?,
        val title: String,
        private val releaseDate : LocalDate?,
        private val userScores : Int,
        val overview : String) : Parcelable {

    fun getReleaseDateAsString() : String? {
        val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH)
        return releaseDate?.format(formatter)
    }

    fun getUserScoresAsString() : String{
        return "$userScores %"
    }

    fun getTitleWithReleaseYear() : String{
        return if (releaseDate != null){
            "$title (${releaseDate.year})"
        } else{
            title
        }
    }


}