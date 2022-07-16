package com.uhi.data.remote

import com.uhi.ui.common.model.MedicineList
import com.uhi.ui.common.model.Question
import com.uhi.ui.common.model.TriagingRequest
import com.uhi.ui.common.model.TriagingResults
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface ApiService {

    @GET("flow/next-question")
    suspend fun getQuestion(@QueryMap since: Map<String, @JvmSuppressWildcards Any?>?): Response<Question?>

    @POST("mobile/get-all-triaging-result")
    suspend fun getResults(@Body since: TriagingRequest?): Response<List<TriagingResults>?>

    @GET("mobile/get-lab-data")
    suspend fun getLabData(): Response<Map<Int, Map<String, Map<String, Float>>>?>

    @GET("mobile/medicines-by-code")
    suspend fun getMedicines(@Query("codes") codes:String): Response<List<MedicineList>>

   @GET("mobile/lab-tests-by-codes")
    suspend fun getTestReport(@Query("codes") codes:String): Response<List<MedicineList>>

}
