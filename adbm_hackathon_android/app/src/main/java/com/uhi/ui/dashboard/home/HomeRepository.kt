package com.uhi.ui.dashboard.home

import com.uhi.data.local.database.Database
import com.uhi.data.local.pref.Preference
import com.uhi.data.remote.Api
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: Api,
    private val preference: Preference,
    private val database: Database
) {

    fun getRepository(page: Int) = flow {
        emit(api.getRepository(page))
    }
}