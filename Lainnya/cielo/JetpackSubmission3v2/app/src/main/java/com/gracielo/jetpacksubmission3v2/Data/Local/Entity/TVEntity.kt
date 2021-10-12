package com.gracielo.jetpacksubmission3v2.Data.Local.Entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "TVFavs")
data class TVEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "TVId")
    var id: Int,

    @ColumnInfo(name = "JudulTV")
    var judul: String,

    @ColumnInfo(name = "DescTV")
    var desc: String,

    @ColumnInfo(name = "PhotoTV")
    var photo: String,

    @ColumnInfo(name = "KategoriTV")
    var kategori: String,

    @ColumnInfo(name = "TahunTV")
    var tahun: String,

    @ColumnInfo(name = "RatingTV")
    var rating: Float,

    @ColumnInfo(name = "GenreTV")
    var genre: String,

    @NonNull
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
){
    companion object {
        const val TYPE_MOVIE = "Movies"
        const val TYPE_TV_SHOW = "TV"
    }
}