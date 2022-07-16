package com.uhi.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uhi.data.local.pref.Preference
import com.uhi.data.remote.Api
import com.uhi.data.remote.ApiResponse
import com.uhi.ui.common.model.LabTest
import com.uhi.ui.common.model.Question
import com.uhi.utils.extention.whenFailed
import com.uhi.utils.extention.whenSuccess
import com.uhi.utils.listener.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val api: Api,
    private val preference: Preference
) : ViewModel() {

    private val _apiState = SingleLiveEvent<ApiResponse<Question>>()
    val apiState: LiveData<ApiResponse<Question>>
        get() = _apiState

    private val _resultApiState = SingleLiveEvent<ApiResponse<Map<String, String>?>>()
    val resultApiState: LiveData<ApiResponse<Map<String, String>?>>
        get() = _resultApiState

    private val _labDataApiState = SingleLiveEvent<ApiResponse<List<LabTest>>>()
    val labDataApiState: LiveData<ApiResponse<List<LabTest>>>
        get() = _labDataApiState

    private var job: Job? = null

    val previousClassifications = HashMap<String, String?>()



    fun getQuestion(questionId: Int? = null, answer: String? = null, isRefresh: Boolean = false) {
        _apiState.value = ApiResponse.Loading(isRefresh)
        val map = HashMap<String, Any?>()
        map["preferredLanguage"] = preference.getAppLanguage()
        questionId?.let { map["questionId"] = it }
        answer?.let { map["answer"] = it }
        job = viewModelScope.launch {
            _apiState.value = api.getQuestion(map)
        }
    }

    fun getResult(list: List<Question?>) {
        _resultApiState.value = ApiResponse.Loading(isRefresh = true)
        val map = HashMap<String, String?>()
        list.forEach {
            map[it?.id.toString()] = it?.answer
        }
        job = viewModelScope.launch {
            _resultApiState.value = api.getResults(map, previousClassifications).whenSuccess {
                it?.let { it1 -> previousClassifications.putAll(it1) }
            }
        }
    }

    fun getLabData(patientId: Int) {
        _labDataApiState.value = ApiResponse.Loading()
        job = viewModelScope.launch {
            val response = api.getLabData()
            response.whenSuccess { map ->
                val testData = ArrayList<LabTest>()
                map?.get(patientId)?.forEach {
                    val labTest = LabTest()
                    labTest.title = it.key
                    val labTestData = ArrayList<LabTest.LabTestData>()
                    it.value.forEach { (t, u) ->
                        labTestData.add(LabTest.LabTestData(t, u))
                    }
                    labTest.labTestData = labTestData
                    testData.add(labTest)
                }
                _labDataApiState.value=ApiResponse.Success(testData)
            }
            response.whenFailed {
                _labDataApiState.value = when (response) {
                    is ApiResponse.ApiError -> ApiResponse.ApiError(response.apiErrorMessage)
                    is ApiResponse.NoInternetConnection -> ApiResponse.NoInternetConnection
                    is ApiResponse.ServerError -> ApiResponse.ServerError(response.errorMessage)
                    else -> ApiResponse.ServerError("")
                }
            }
        }
    }
}