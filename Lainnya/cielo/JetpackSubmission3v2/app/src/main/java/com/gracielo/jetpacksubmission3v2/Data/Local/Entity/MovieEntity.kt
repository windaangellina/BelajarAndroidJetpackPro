package com.gracielo.jetpacksubmission3v2.Data.Local.Entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieFavs")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "MovieId")
    var id: Int,

    @ColumnInfo(name = "JudulMovie")
    var judul: String,

    @ColumnInfo(name = "DescMovie")
    var desc: String,

    @ColumnInfo(name = "PhotoMovie")
    var photo: String,

    @ColumnInfo(name = "KategoriMovie")
    var kategori: String,

    @ColumnInfo(name = "TahunMovie")
    var tahun: String,

    @ColumnInfo(name = "RatingMovie")
    var rating: Float,

    @ColumnInfo(name = "GenreMovie")
    var genre: String,

    @NonNull
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false


) {
    companion object {
        const val TYPE_MOVIE = "Movies"
        const val TYPE_TV_SHOW = "TV"
    }
}