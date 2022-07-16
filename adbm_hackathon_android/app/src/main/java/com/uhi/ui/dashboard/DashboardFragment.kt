package com.uhi.ui.dashboard

import android.view.View
import com.uhi.R
import com.uhi.data.local.pref.PreferenceManager
import com.uhi.data.local.pref.PreferenceManager.Companion.EN
import com.uhi.data.local.pref.PreferenceManager.Companion.GU
import com.uhi.data.local.pref.PreferenceManager.Companion.HN
import com.uhi.databinding.FragmentDashboardBinding
import com.uhi.ui.common.base.BaseFragment
import com.uhi.ui.common.languageList
import com.uhi.utils.extention.getKey
import com.uhi.utils.extention.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

    override fun initView() {
        binding.autoCompleteTextView.setSimpleItems(languageList)
        binding.autoCompleteTextView.setText(
            when (preference.getAppLanguage()) {
                EN -> languageList[0]
                HN -> languageList[1]
                GU -> languageList[3]
                else -> ""
            }, false
        )
        binding.autoCompleteTextView.setOnItemClickListener { adapterView, view, i, l ->
            preference.setAppLanguage(
                when (i) {
                    0 -> EN
                    1 -> HN
                    else -> GU
                }
            )
            restartApp()
        }
    }

    override fun initListener() {
        binding.symptomsCardView.setOnClickListener(this)
        binding.timeSeriesCardView.setOnClickListener(this)
    }

    override fun initObserver() {
    }

    override fun onClick(view: View?) {
        super.onClick(view)
        when (view?.id) {
            R.id.symptomsCardView -> navigate(R.id.action_dashboardFragment_to_questionsFragment)
            R.id.timeSeriesCardView -> navigate(R.id.action_dashboardFragment_to_patientFragment)
        }
    }
}