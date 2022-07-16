package com.uhi.data.remote

import com.uhi.ui.common.model.MedicineList
import com.uhi.ui.common.model.Question
import com.uhi.ui.common.model.TriagingResults
import java.util.*

interface Api {

    suspend fun getQuestion(request: Map<String, Any?>): ApiResponse<Question?>

    suspend fun getResults(request: Map<String, Any?>, previousClassifications: Map<String, Any?>): ApiResponse<List<TriagingResults>?>

    suspend fun getLabData(): ApiResponse<Map<Int, Map<String, Map<String, Float>>>?>

    suspend fun getMedicine(code: String): ApiResponse<List<MedicineList>>

    suspend fun getTestReport(code: String): ApiResponse<List<MedicineList>>
}
