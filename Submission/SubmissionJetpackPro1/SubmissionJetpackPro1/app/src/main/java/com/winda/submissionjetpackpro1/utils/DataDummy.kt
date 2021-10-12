package com.winda.submissionjetpackpro1.utils

import android.content.Context
import android.content.res.TypedArray
import com.winda.submissionjetpackpro1.R
import com.winda.submissionjetpackpro1.data.Show
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DataDummy {
    fun generateRandomDataDummy() : ArrayList<Show>{
        val shows = ArrayList<Show>()

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val date = LocalDate.parse("10/01/2019", formatter)

        shows.add(Show(
            1, 1, 1, "hello world", 2, 15, date,
                "PG-13",98, "world is ours", "https://youtu.be/S12-4mXCNj4",
                "a journey on how to take over the world"
        ))

        shows.add(Show(
                2, 1, 1, "kotlin is the best", 2, 15, date,
                "PG-13",98, "kotlin is our best friend", "https://youtu.be/S12-4mXCNj4",
                "a movie for kotlin lovers"
        ))

        shows.add(Show(
                3, 1, 1, "hello world 2", 2, 15, date,
                "PG-13",98, "world is ours", "https://youtu.be/S12-4mXCNj4",
                "a journey on how to take over the world (the sequel)"
        ))

        return shows
    }

    fun generateDataDummyFromResources(context: Context, categoryId : Int, filterByCategory : Boolean) : ArrayList<Show> {
        val shows = ArrayList<Show>()
        prepareResources(context)

        for (i in dataId.indices){
            val show = Show(
                    dataId[i].toInt(),
                    dataCategoryId[i].toInt(),
                    dataPoster.getResourceId(i, -1),
                    dataTitle[i],
                    dataDurationHour[i].toInt(),
                    dataDurationMinute[i].toInt(),
                    getDate(dataReleaseDate[i]),
                    dataRatings[i],
                    dataUserScores[i].toInt(),
                    dataTagline[i],
                    dataTrailer[i],
                    dataOverview[i]
            )

            var valid = true
            if (filterByCategory && categoryId != show.categoryId) {
                valid = false
            }

            if (valid) shows.add(show)
        }
        return shows
    }

    // string resources
    private lateinit var dataId : Array<String>
    private lateinit var dataCategoryId : Array<String>
    private lateinit var dataPoster : TypedArray
    private lateinit var dataTitle : Array<String>
    private lateinit var dataDurationHour : Array<String>
    private lateinit var dataDurationMinute : Array<String>
    private lateinit var dataReleaseDate : Array<String>
    private lateinit var dataRatings : Array<String>
    private lateinit var dataUserScores : Array<String>
    private lateinit var dataTagline : Array<String>
    private lateinit var dataTrailer : Array<String>
    private lateinit var dataOverview : Array<String>

    // get resources
    private fun prepareResources(context: Context){
        dataId = context.resources.getStringArray(R.array.show_id)
        dataCategoryId = context.resources.getStringArray(R.array.show_category)
        dataTitle = context.resources.getStringArray(R.array.show_title)
        dataDurationHour = context.resources.getStringArray(R.array.show_duration_hour)
        dataDurationMinute = context.resources.getStringArray(R.array.show_duration_minute)
        dataReleaseDate = context.resources.getStringArray(R.array.show_release_date)
        dataRatings = context.resources.getStringArray(R.array.show_ratings)
        dataUserScores = context.resources.getStringArray(R.array.show_user_scores)
        dataTagline = context.resources.getStringArray(R.array.show_tagline)
        dataTrailer = context.resources.getStringArray(R.array.show_link_trailer)
        dataOverview = context.resources.getStringArray(R.array.show_overview)

        dataPoster = context.resources.obtainTypedArray(R.array.show_poster)
    }

    private fun getDate(dateString : String) : LocalDate{
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return LocalDate.parse(dateString, formatter)
    }
}