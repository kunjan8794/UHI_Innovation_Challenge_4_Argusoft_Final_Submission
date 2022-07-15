package com.uhi.ui.dashboard.quetion

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uhi.data.local.pref.Preference
import com.uhi.data.remote.Api
import com.uhi.data.remote.ApiResponse
import com.uhi.ui.common.model.Album
import com.uhi.ui.common.model.Question
import com.uhi.utils.extention.whenSuccess
import com.uhi.utils.listener.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val api: Api,
    private val preference: Preference
) : ViewModel() {

    private val _apiState = SingleLiveEvent<ApiResponse<Question>>()
    val apiState: LiveData<ApiResponse<Question>>
        get() = _apiState

    private val _resultApiState = SingleLiveEvent<ApiResponse<Map<String, String>?>>()
    val resultApiState: LiveData<ApiResponse<Map<String, String>?>>
        get() = _resultApiState
    private var job: Job? = null
    val previousClassifications = HashMap<String, String?>()

    init {
        getQuestion()
    }

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
}