package com.example.submission1.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.submission1.codeclass.Film

@Dao
interface DBDao {
    @Query("SELECT * FROM film")
    fun getFavorite(): LiveData<List<Film>>

    @Insert
    suspend fun insertFavorite(film: Film)

    @Delete
    suspend fun deleteFavorite(film: Film)
}