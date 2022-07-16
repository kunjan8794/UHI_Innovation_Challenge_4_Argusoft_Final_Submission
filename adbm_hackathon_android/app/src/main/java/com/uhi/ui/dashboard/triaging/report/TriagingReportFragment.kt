package com.uhi.ui.dashboard.triaging.report

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.uhi.R
import com.uhi.databinding.FragmentTriagingBinding
import com.uhi.ui.common.INTENT_EXTRA_CODE
import com.uhi.ui.common.INTENT_EXTRA_IS_MEDICINE
import com.uhi.ui.common.INTENT_EXTRA_QUESTIONS
import com.uhi.ui.common.base.BaseFragment
import com.uhi.ui.common.model.Question
import com.uhi.ui.dashboard.DashboardViewModel
import com.uhi.utils.extention.*
import com.uhi.utils.glide.GlideApp
import com.uhi.utils.glide.GlideRequests
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TriagingReportFragment : BaseFragment<FragmentTriagingBinding>() {

    private lateinit var triagingReportAdapter: TriagingReportAdapter
    private lateinit var glideRequests: GlideRequests
    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardViewModel.getMedicine(requireArguments().getString(INTENT_EXTRA_CODE, ""), requireArguments().getBoolean(INTENT_EXTRA_IS_MEDICINE))
    }

    override fun initView() {
        glideRequests = GlideApp.with(this)
        if (requireArguments().getBoolean(INTENT_EXTRA_IS_MEDICINE, false)) {
            binding.titleTextView.setText(R.string.title_medicine)
        } else {
            binding.titleTextView.setText(R.string.title_test_report)
        }
        triagingReportAdapter = TriagingReportAdapter(arrayListOf())
        binding.recyclerView.adapter = triagingReportAdapter
        binding.progressLayout.recyclerView = binding.recyclerView
    }

    override fun initListener() {

    }

    override fun initObserver() {
        observeNotNull(dashboardViewModel.medicineApiState) { apiResponse ->
            apiResponse.handleListApiView(binding.progressLayout, onClickListener = {
                dashboardViewModel.getMedicine(requireArguments().getString(INTENT_EXTRA_CODE, ""), requireArguments().getBoolean(INTENT_EXTRA_IS_MEDICINE))
            }) {
                it?.let { it1 -> triagingReportAdapter.addAll(it1) }
            }
        }

    }

    override fun onClick(view: View?) {
        super.onClick(view)
        when (view?.id) {

        }
    }
}