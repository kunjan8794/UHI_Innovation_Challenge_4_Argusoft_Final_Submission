package com.uhi.ui.dashboard.timeseries

import android.view.View
import com.uhi.R
import com.uhi.data.local.pref.PreferenceManager
import com.uhi.data.local.pref.PreferenceManager.Companion.EN
import com.uhi.data.local.pref.PreferenceManager.Companion.GU
import com.uhi.data.local.pref.PreferenceManager.Companion.HN
import com.uhi.databinding.FragmentDashboardBinding
import com.uhi.databinding.FragmentPatientBinding
import com.uhi.ui.common.INTENT_EXTRA_PATIENT_ID
import com.uhi.ui.common.base.BaseFragment
import com.uhi.ui.common.languageList
import com.uhi.utils.extention.getKey
import com.uhi.utils.extention.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PatientFragment : BaseFragment<FragmentPatientBinding>() {

    override fun initView() {
        binding.headerLayout.toolbar.setTitle(R.string.label_time_series)
    }

    override fun initListener() {
        binding.headerLayout.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.andersonCardView.setOnClickListener(this)
        binding.daniellaCardView.setOnClickListener(this)
    }

    override fun initObserver() {
    }

    override fun onClick(view: View?) {
        super.onClick(view)
        when (view?.id) {
            R.id.andersonCardView -> {
                navigate(R.id.action_patientFragment_to_patientReportFragment) {
                    putInt(INTENT_EXTRA_PATIENT_ID, 1)
                }
            }
            R.id.daniellaCardView -> {
                navigate(R.id.action_patientFragment_to_patientReportFragment) {
                    putInt(INTENT_EXTRA_PATIENT_ID, 2)
                }
            }
        }
    }
}