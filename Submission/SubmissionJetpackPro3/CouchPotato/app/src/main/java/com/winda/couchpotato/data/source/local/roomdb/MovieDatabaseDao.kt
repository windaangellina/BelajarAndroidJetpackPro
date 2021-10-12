package com.winda.couchpotato.data.source.local.roomdb

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.winda.couchpotato.data.Show

@Dao
interface MovieDatabaseDao {
    @Insert
    suspend fun insertFavorite(show: Show)

    @Delete
    suspend fun deleteFavorite(show: Show)

    @Query("select * from ${Show.TABLE_NAME}")
    fun getListFavorite() : DataSource.Factory<Int, Show>

    @Query("select count(*) from ${Show.TABLE_NAME} where id=:id")
    fun getCountFavoriteById(id : Int) : LiveData<Int>
}