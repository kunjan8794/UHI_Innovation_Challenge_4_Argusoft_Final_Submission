package com.uhi.data.remote

import com.uhi.ui.common.model.Question

interface Api {

    suspend fun getQuestion(request: Map<String, Any?>): ApiResponse<Question>

    suspend fun getResults(request: Map<String, Any?>,previousClassifications: Map<String, Any?>): ApiResponse<Map<String, String>?>
}
