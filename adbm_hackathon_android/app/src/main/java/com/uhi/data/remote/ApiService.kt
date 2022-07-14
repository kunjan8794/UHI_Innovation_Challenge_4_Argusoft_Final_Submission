package com.uhi.data.remote

import com.uhi.ui.common.model.Album
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/photos")
    suspend fun getRepositories(@Query("albumId") since: Any?): Response<List<Album>>

}
