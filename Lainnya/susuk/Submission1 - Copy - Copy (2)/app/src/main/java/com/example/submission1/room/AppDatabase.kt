package com.example.submission1.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.submission1.codeclass.Film

@Database(entities = [Film::class],
    version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dbDao() : DBDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Favorite.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}