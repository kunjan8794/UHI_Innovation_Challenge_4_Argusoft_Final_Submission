package com.uhi.data.remote

import com.uhi.ui.common.model.Question
import com.uhi.ui.common.model.TriagingRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap
import java.util.*

interface ApiService {

    @GET("flow/next-question")
    suspend fun getQuestion(@QueryMap since: Map<String, @JvmSuppressWildcards Any?>?): Response<Question>

    @POST("mobile/get-triaging-result")
    suspend fun getResults(@Body since: TriagingRequest?): Response<Map<String, String>?>

    @GET("mobile/get-lab-data")
    suspend fun getLabData(): Response<Map<Int, Map<String, Map<Date, Float>>>?>

}
