package com.uhi.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uhi.data.local.pref.Preference
import com.uhi.data.remote.Api
import com.uhi.data.remote.ApiResponse
import com.uhi.ui.common.model.LabTest
import com.uhi.ui.common.model.MedicineList
import com.uhi.ui.common.model.Question
import com.uhi.ui.common.model.TriagingResults
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

    private val _apiState = SingleLiveEvent<ApiResponse<Question?>>()
    val apiState: LiveData<ApiResponse<Question?>>
        get() = _apiState

    private val _resultApiState = MutableLiveData<ApiResponse<List<TriagingResults>?>>()
    val resultApiState: LiveData<ApiResponse<List<TriagingResults>?>>
        get() = _resultApiState

    private val _labDataApiState = MutableLiveData<ApiResponse<List<LabTest>>>()
    val labDataApiState: LiveData<ApiResponse<List<LabTest>>>
        get() = _labDataApiState

    private val _medicineApiState = MutableLiveData<ApiResponse<List<MedicineList>>>()
    val medicineApiState: LiveData<ApiResponse<List<MedicineList>>>
        get() = _medicineApiState

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
        _resultApiState.value = ApiResponse.Loading()
        val map = HashMap<String, String?>()
        list.forEach {
            map[it?.id.toString()] = it?.answer
        }
        job = viewModelScope.launch {
            _resultApiState.value = api.getResults(map, previousClassifications)
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
                _labDataApiState.value = ApiResponse.Success(testData)
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


    fun getMedicine(code: String, isMedicine: Boolean) {
        _medicineApiState.value = ApiResponse.Loading()
        job = viewModelScope.launch {
            _medicineApiState.value = if (isMedicine) api.getMedicine(code) else api.getTestReport(code)
        }
    }
}