package com.uhi.ui.dashboard.timeseries

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.uhi.databinding.FragmentPatientReportBinding
import com.uhi.ui.common.INTENT_EXTRA_PATIENT_ID
import com.uhi.ui.common.base.BaseFragment
import com.uhi.ui.dashboard.DashboardViewModel
import com.uhi.utils.extention.*
import com.uhi.utils.glide.GlideApp
import com.uhi.utils.glide.GlideRequests
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PatientReportFragment : BaseFragment<FragmentPatientReportBinding>() {

    private lateinit var triagingAdapter: PatientReportAdapter
    private lateinit var glideRequests: GlideRequests
    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardViewModel.getLabData(requireArguments().getInt(INTENT_EXTRA_PATIENT_ID))
    }

    override fun initView() {
        glideRequests = GlideApp.with(this)
        triagingAdapter = PatientReportAdapter(arrayListOf(), glideRequests, this)
    }

    override fun initListener() {

    }

    override fun initObserver() {
        observeNotNull(dashboardViewModel.labDataApiState) { apiResponse ->
            apiResponse.handleListApiView(binding.progressLayout, onClickListener = {
                dashboardViewModel.getQuestion()
            }) {
                it?.let { it1 -> triagingAdapter.addAll(it1) }
                it.timber()
            }
        }

    }

    override fun onClick(view: View?) {
        super.onClick(view)
        when (view?.id) {

        }
    }
}