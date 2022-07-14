package com.uhi.ui.dashboard.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uhi.data.remote.ApiResponse
import com.uhi.ui.common.model.Album
import com.uhi.utils.listener.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _apiState = SingleLiveEvent<ApiResponse<List<Album>>>()
    val apiState: LiveData<ApiResponse<List<Album>>>
        get() = _apiState
    private var page = 1
    private var job: Job? = null

    fun getRepository(isRefresh: Boolean = false, isLoadMore: Boolean = false) {
        _apiState.value = ApiResponse.Loading(isRefresh, isLoadMore)
        if (isLoadMore) page++ else page = 1
        job = viewModelScope.launch {
            homeRepository.getRepository(page).collect {
                _apiState.value = it
            }
        }
    }
}