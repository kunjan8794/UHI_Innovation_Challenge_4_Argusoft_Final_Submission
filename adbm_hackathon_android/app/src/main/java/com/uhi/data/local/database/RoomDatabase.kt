package com.uhi.data.local.database

import androidx.room.Database
import com.uhi.ui.common.model.Album

@Database(entities = [Album::class], version = 1, exportSchema = false)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun dao(): Dao
}