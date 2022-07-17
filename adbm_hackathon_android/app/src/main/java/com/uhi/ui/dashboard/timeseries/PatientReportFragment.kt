package com.uhi.ui.dashboard.timeseries

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.uhi.R
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

    private lateinit var patientReportAdapter: PatientReportAdapter
    private lateinit var glideRequests: GlideRequests
    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardViewModel.getLabData(requireArguments().getInt(INTENT_EXTRA_PATIENT_ID))
    }

    override fun initView() {
        glideRequests = GlideApp.with(this)
        binding.headerLayout.toolbar.setTitle(R.string.report)
        patientReportAdapter = PatientReportAdapter(arrayListOf(), glideRequests, this)
        binding.recyclerView.adapter = patientReportAdapter
    }

    override fun initListener() {
        binding.headerLayout.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun initObserver() {
        observeNotNull(dashboardViewModel.labDataApiState) { apiResponse ->
            apiResponse.handleListApiView(binding.progressLayout, onClickListener = {
                dashboardViewModel.getLabData(requireArguments().getInt(INTENT_EXTRA_PATIENT_ID))
            }) {
                it?.let { it1 -> patientReportAdapter.addAll(it1) }
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