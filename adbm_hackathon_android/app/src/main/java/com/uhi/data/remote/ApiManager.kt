package com.uhi.data.remote

import com.uhi.BuildConfig
import com.uhi.data.local.pref.Preference
import com.uhi.ui.common.model.MedicineList
import com.uhi.ui.common.model.Question
import com.uhi.ui.common.model.TriagingRequest
import com.uhi.ui.common.model.TriagingResults
import com.uhi.utils.common.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class ApiManager(
    private val networkHelper: NetworkHelper,
    private val preference: Preference
) : Api {

    private val apiService: ApiService by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
        return@lazy Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient.build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    override suspend fun getQuestion(request: Map<String, Any?>): ApiResponse<Question?> {
        return executeApiHelper(networkHelper) { apiService.getQuestion(request) }
    }

    override suspend fun getResults(request: Map<String, Any?>, previousClassifications: Map<String, Any?>): ApiResponse<List<TriagingResults>?> {
        return executeApiHelper(networkHelper) { apiService.getResults(TriagingRequest(request, previousClassifications, preference.getAppLanguage())) }
    }

    override suspend fun getLabData(): ApiResponse<Map<Int, Map<String, Map<String, Float>>>?> {
        return executeApiHelper(networkHelper) { apiService.getLabData() }
    }

    override suspend fun getMedicine(code: String): ApiResponse<List<MedicineList>> {
        return executeApiHelper(networkHelper) { apiService.getMedicines(code) }
    }

    override suspend fun getTestReport(code: String): ApiResponse<List<MedicineList>> {
        return executeApiHelper(networkHelper) { apiService.getTestReport(code) }
    }
}



