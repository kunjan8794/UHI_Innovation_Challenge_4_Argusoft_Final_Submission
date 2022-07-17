package com.uhi.ui.dashboard.triaging.report

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.uhi.R
import com.uhi.databinding.FragmentTriagingBinding
import com.uhi.databinding.FragmentTriagingReportBinding
import com.uhi.ui.common.INTENT_EXTRA_CODE
import com.uhi.ui.common.INTENT_EXTRA_IS_MEDICINE
import com.uhi.ui.common.INTENT_EXTRA_QUESTIONS
import com.uhi.ui.common.INTENT_EXTRA_TRIAGING
import com.uhi.ui.common.base.BaseFragment
import com.uhi.ui.common.model.Question
import com.uhi.ui.common.model.TriagingResults
import com.uhi.ui.dashboard.DashboardViewModel
import com.uhi.ui.dashboard.triaging.getString
import com.uhi.utils.extention.*
import com.uhi.utils.glide.GlideApp
import com.uhi.utils.glide.GlideRequests
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder


@AndroidEntryPoint
class TriagingReportFragment : BaseFragment<FragmentTriagingReportBinding>() {

    private lateinit var triagingReportAdapter: TriagingReportAdapter
    private lateinit var glideRequests: GlideRequests
    private val dashboardViewModel: DashboardViewModel by viewModels()
    private var triagingResults: TriagingResults? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardViewModel.getMedicine(requireArguments().getString(INTENT_EXTRA_CODE, ""), requireArguments().getBoolean(INTENT_EXTRA_IS_MEDICINE))
    }

    override fun initView() {
        glideRequests = GlideApp.with(this)
        triagingResults = requireArguments().getParcelable<TriagingResults>(INTENT_EXTRA_TRIAGING)
        if (requireArguments().getBoolean(INTENT_EXTRA_IS_MEDICINE, false)) {
            binding.headerLayout.toolbar.title = getString(R.string.title_medicine, triagingResults?.disease)
        } else {
            binding.headerLayout.toolbar.title = getString(R.string.title_test_report, triagingResults?.disease)
        }
        binding.headerLayout.toolbar.inflateMenu(R.menu.menu_share)
        triagingReportAdapter = TriagingReportAdapter(arrayListOf())
        binding.recyclerView.adapter = triagingReportAdapter
        binding.progressLayout.recyclerView = binding.recyclerView
    }

    override fun initListener() {
        binding.headerLayout.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.headerLayout.toolbar.setOnMenuItemClickListener {
            val stringBuilder = StringBuilder()
            stringBuilder.append(triagingResults?.disease).append("\n\n\n")
            stringBuilder.append(getString(com.uhi.R.string.label_symptoms)).append("\n")
            stringBuilder.append(triagingResults?.symptoms?.getString()).append("\n\n")
            stringBuilder.append(getString(com.uhi.R.string.label_suggestions)).append("\n")
            stringBuilder.append(triagingResults?.suggestions?.getString()).append("\n\n")
            if (requireArguments().getBoolean(INTENT_EXTRA_IS_MEDICINE, false)) {
                stringBuilder.append(getString(R.string.title_medicine, triagingResults?.disease)).append("\n")
            } else {
                stringBuilder.append(getString(R.string.title_test_report, triagingResults?.disease)).append("\n")
            }
            triagingReportAdapter.getItemsList().forEach {
                stringBuilder.append(it?.name).append("\n")
                if (it?.dose?.isNotEmpty() == true)
                    stringBuilder.append(it.dose).append("\n")
                stringBuilder.append("\n")
            }
            val intent = Intent(Intent.ACTION_SEND)
            val shareBody = stringBuilder.toString()
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(intent)
            return@setOnMenuItemClickListener true
        }
    }

    override fun initObserver() {
        dashboardViewModel.medicineApiState.observe(this) { apiResponse ->
            apiResponse.handleListApiView(binding.progressLayout,
                skipIds = listOf(R.id.noteTextView),
                msgResId = if (requireArguments().getBoolean(INTENT_EXTRA_IS_MEDICINE, false)) {
                    getString(R.string.no_found_medicins)
                } else {
                    getString(R.string.no_found_test_rports)
                },
                onClickListener = {
                    dashboardViewModel.getMedicine(requireArguments().getString(INTENT_EXTRA_CODE, ""), requireArguments().getBoolean(INTENT_EXTRA_IS_MEDICINE))
                }) {
                it?.let { it1 ->
                    triagingReportAdapter.addAll(it1)
                    requireActivity().runOnUiThread {
                        if (it1.isNotEmpty()) {
                            if ((requireArguments().getParcelableArrayList<Question?>(INTENT_EXTRA_QUESTIONS)?.find {
                                    it.answer?.contains("DIABETIC") == true || it.answer?.contains("HYPERTENSTION") == true
                                            || it.answer?.contains("ALLERGIC_TO_SUBSTANCES") == true
                                } != null) && requireArguments().getBoolean(INTENT_EXTRA_IS_MEDICINE, false))
                                context?.alertDialog {
                                    setCancelable(false)
                                    setTitle("Alert")
                                    setMessage("The patient is either hypertensive/allergic to specific substance/diabetic. Some of these medications may have reactions to their current medication. We suggest you prescribe based on their current medication.")
                                    setPositiveButton("Ok") { dialog, which -> dialog.dismiss() }
                                }?.show()
                        }
                    }
                }
            }
        }


    }

    override fun onClick(view: View?) {
        super.onClick(view)
        when (view?.id) {

        }
    }
}