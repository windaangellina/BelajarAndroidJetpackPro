package com.winda.couchpotato.data.source.local.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.winda.couchpotato.data.Show

@Database(entities = [Show::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDatabaseDao
    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null

        // singleton pattern -> instance database hanya diinisialisasi 1x saja
        fun getAppDatabase(context: Context) : AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "favorite_db"
                ).build()
                INSTANCE = instance
                instance//this return instance
            }
        }
    }
}