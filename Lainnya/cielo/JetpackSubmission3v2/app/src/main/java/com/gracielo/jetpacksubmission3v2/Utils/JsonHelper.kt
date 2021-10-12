package com.gracielo.jetpacksubmission3v2.Utils

import android.content.Context
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.FilmEntity.Companion.TYPE_MOVIE
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.FilmEntity.Companion.TYPE_TV_SHOW
import com.gracielo.jetpacksubmission3v2.Data.Source.Remote.Response.MovieResponse
import com.gracielo.jetpacksubmission3v2.Data.Source.Remote.Response.TVResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovies(): List<MovieResponse> {
        val list = ArrayList<MovieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("movie_response.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val response = listArray.getJSONObject(i)

                val id: Int = response.getInt("id")
                // Judul
                val judul = response.getString("title")
                // desc
                val desc = response.getString("overview")
                //photo
                val photo = response.getString("poster_path")

                val kategori = TYPE_MOVIE

                var tahun = ""
                // Rating
                val rating = response.getDouble("vote_average").toFloat() / 2

                //Genre
                val genre = response.getJSONArray("genre_ids")
                val dataGenres = DataGenres.genres
                var film_genre = ""
                var ctr = 0
                while (ctr < genre.length()) {
                    if (film_genre != "") {
                        film_genre = "$film_genre, "
                    }
                    val idgenre = genre[ctr]
                    film_genre = "$film_genre " + dataGenres[idgenre]
                    ctr++
                }

                tahun = response.getString("release_date")
                tahun = tahun.substring(0, 4)
                list.add(MovieResponse(id, judul, desc, photo, kategori, tahun, rating, film_genre))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadTV(): List<TVResponse> {
        val list = ArrayList<TVResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("tvshow_response.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val response = listArray.getJSONObject(i)

                val id: Int = response.getInt("id")
                // Judul
                val judul = response.getString("name")

                // desc
                val desc = response.getString("overview")
                //photo
                val photo = response.getString("poster_path")

                val kategori = TYPE_TV_SHOW

                var tahun = ""
                // Rating
                val rating = response.getDouble("vote_average").toFloat() / 2

                //Genre
                val genre = response.getJSONArray("genre_ids")
                val dataGenres = DataGenres.genres
                var film_genre = ""
                var ctr = 0
                while (ctr < genre.length()) {
                    if (film_genre != "") {
                        film_genre = "$film_genre, "
                    }
                    val idgenre = genre[ctr]
                    film_genre = "$film_genre " + dataGenres[idgenre]
                    ctr++
                }

                tahun = response.getString("first_air_date")
                tahun = tahun.substring(0, 4)
                list.add(TVResponse(id, judul, desc, photo, kategori, tahun, rating, film_genre))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }
}