package com.gracielo.jetpacksubmission3v2.Data.Local.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.MovieEntity
import com.gracielo.jetpacksubmission3v2.Data.Local.Entity.TVEntity

@Database(entities = [MovieEntity::class, TVEntity::class], version = 1, exportSchema = false)
abstract class FilmDatabase : RoomDatabase(){
    abstract fun filmDao(): FilmDao

    companion object {

        @Volatile
        private var INSTANCE: FilmDatabase? = null

        fun getInstance(context: Context): FilmDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    FilmDatabase::class.java,
                    "Film.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}