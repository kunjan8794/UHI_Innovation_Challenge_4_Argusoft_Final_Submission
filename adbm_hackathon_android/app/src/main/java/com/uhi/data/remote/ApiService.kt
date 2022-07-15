package com.uhi.data.remote

import com.uhi.ui.common.model.Album
import com.uhi.ui.common.model.Question
import com.uhi.ui.common.model.TriagingRequest
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("/photos")
    suspend fun getRepositories(@Query("albumId") since: Any?): Response<List<Album>>

    @GET("flow/next-question")
    suspend fun getQuestion(@QueryMap since: Map<String, @JvmSuppressWildcards Any?>?): Response<Question>

    @POST("mobile/get-triaging-result")
    suspend fun getResults(@Body since: TriagingRequest?): Response<Map<String, String>?>

}
