package com.uhi.data.remote

import com.squareup.moshi.Moshi
import com.uhi.BuildConfig
import com.uhi.ui.common.model.Question
import com.uhi.ui.common.model.TriagingRequest
import com.uhi.utils.common.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class ApiManager(private val networkHelper: NetworkHelper) : Api {

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
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(CustomDateAdapter()).build()))
            .build()
            .create(ApiService::class.java)
    }

    override suspend fun getQuestion(request: Map<String, Any?>): ApiResponse<Question> {
        return executeApiHelper(networkHelper) { apiService.getQuestion(request) }
    }

    override suspend fun getResults(request: Map<String, Any?>,previousClassifications: Map<String, Any?>): ApiResponse<Map<String, String>?> {
        return executeApiHelper(networkHelper) { apiService.getResults(TriagingRequest(request,previousClassifications)) }
    }

    override suspend fun getLabData(): ApiResponse<Map<Int, Map<String, Map<Date, Float>>>?> {
        return executeApiHelper(networkHelper) { apiService.getLabData() }
    }
}



