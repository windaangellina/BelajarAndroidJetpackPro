package com.winda.couchpotato.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.winda.couchpotato.utils.FunctionLibrary
import kotlinx.parcelize.Parcelize
import java.time.format.DateTimeFormatter
import java.util.*

@Entity(tableName = Show.TABLE_NAME)
@Parcelize
class Show (
    @PrimaryKey
    val id : Int,
    val posterUrl : String?,
    val backdropUrl : String?,
    val title: String,
    val releaseDateEpoch : Long,
    val userScores : Int,
    val overview : String) : Parcelable {

    companion object {
        const val TABLE_NAME = "favorite_show"
    }

    fun getReleaseDateAsString() : String? {
        // unix timestamps uses seconds while java measures time in milliseconds -> hence * 1000
        val date = FunctionLibrary.getLocalDateTimeFromLong(releaseDateEpoch * 1000L)
        val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH)

        return if (releaseDateEpoch == 0L){
            ""
        } else{
            date.format(formatter)
        }
    }

    fun getUserScoresAsString() : String{
        return "$userScores %"
    }

    fun getTitleWithReleaseYear() : String{
        // unix timestamps uses seconds while java measures time in milliseconds -> hence * 1000
        val releaseDate = FunctionLibrary.getLocalDateTimeFromLong(releaseDateEpoch * 1000L)

        return if (releaseDateEpoch == 0L) title
        else{
            "$title (${releaseDate.year})"
        }
    }


}