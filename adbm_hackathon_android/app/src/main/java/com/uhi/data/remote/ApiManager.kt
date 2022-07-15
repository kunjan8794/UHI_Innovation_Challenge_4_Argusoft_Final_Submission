package com.uhi.data.remote

import com.uhi.BuildConfig
import com.uhi.ui.common.model.Album
import com.uhi.ui.common.model.Question
import com.uhi.ui.common.model.TriagingRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiManager : Api {

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

    override suspend fun getRepository(request: Any): ApiResponse<List<Album>> {
        return executeApiHelper { apiService.getRepositories(request) }
    }

    override suspend fun getQuestion(request: Map<String, Any?>): ApiResponse<Question> {
        return executeApiHelper { apiService.getQuestion(request) }
    }

    override suspend fun getResults(request: Map<String, Any?>,previousClassifications: Map<String, Any?>): ApiResponse<Map<String, String>?> {
        return executeApiHelper { apiService.getResults(TriagingRequest(request,previousClassifications)) }
    }
}



