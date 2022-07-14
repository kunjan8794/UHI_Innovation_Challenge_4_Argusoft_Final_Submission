package com.uhi.data.local.database

import com.uhi.ui.common.model.Album

class DatabaseManager(roomDatabase: RoomDatabase) : Database {

    private val dao = roomDatabase.dao()

    override suspend fun getAll(): List<Album> {
        return dao.getAll()
    }

    override suspend fun addAll(list: List<Album>) {
        dao.addAll(list)
    }
}