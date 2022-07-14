package com.uhi.data.local.database

import com.uhi.ui.common.model.Album

interface Database {
    suspend fun getAll(): List<Album>

    suspend fun addAll(list: List<Album>)
}