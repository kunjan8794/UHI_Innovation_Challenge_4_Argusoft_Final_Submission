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
import com.uhi.utils.extention.alertDialog
import com.uhi.utils.extention.getKey
import com.uhi.utils.extention.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

    override fun initView() {
        binding.headerLayout.toolbar.setTitle(R.string.app_name)
        binding.headerLayout.toolbar.inflateMenu(R.menu.menu_dashboard)
    }

    override fun initListener() {
        binding.symptomsCardView.setOnClickListener(this)
        binding.timeSeriesCardView.setOnClickListener(this)
        binding.headerLayout.toolbar.setOnMenuItemClickListener {
            val selectedPosition = when (preference.getAppLanguage()) {
                EN -> 0
                HN -> 1
                GU -> 2
                else -> -1
            }
            context?.alertDialog {
                setTitle(R.string.title_language)
                setSingleChoiceItems(languageList, selectedPosition) { dialogInterface, i ->
                    preference.setAppLanguage(
                        when (i) {
                            0 -> EN
                            1 -> HN
                            else -> GU
                        }
                    )
                    dialogInterface.dismiss()
                    restartApp()
                }
            }?.show()
            return@setOnMenuItemClickListener true
        }
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