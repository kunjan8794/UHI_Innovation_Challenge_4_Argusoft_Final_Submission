package com.uhi.data.remote

import com.uhi.ui.common.model.Album

interface Api {

    suspend fun getRepository(request: Any): ApiResponse<List<Album>>
}
