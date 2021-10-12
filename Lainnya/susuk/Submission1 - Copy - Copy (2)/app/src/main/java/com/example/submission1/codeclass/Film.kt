package com.example.submission1.codeclass

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film")
data class Film (
        @PrimaryKey
        @ColumnInfo(name = "judul")
        var judul: String = "",
        @ColumnInfo(name = "isi")
        var isi: String? = "",
        @ColumnInfo(name = "avatar")
        var avatar: String? = ""
) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString().toString(),
                parcel.readString(),
                parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(judul)
                parcel.writeString(isi)
                parcel.writeString(avatar)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Film> {
                override fun createFromParcel(parcel: Parcel): Film {
                        return Film(parcel)
                }

                override fun newArray(size: Int): Array<Film?> {
                        return arrayOfNulls(size)
                }
        }
}