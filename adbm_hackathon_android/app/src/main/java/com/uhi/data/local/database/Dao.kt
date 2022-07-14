package com.uhi.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uhi.ui.common.model.Album

@Dao
interface Dao {

    @Query("SELECT * FROM album")
    suspend fun getAll(): List<Album>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(list: List<Album>)
}