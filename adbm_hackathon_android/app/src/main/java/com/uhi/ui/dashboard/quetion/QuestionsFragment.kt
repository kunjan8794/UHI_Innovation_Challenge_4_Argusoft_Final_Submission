package com.uhi.ui.dashboard.quetion

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.uhi.R
import com.uhi.data.local.pref.PreferenceManager.Companion.EN
import com.uhi.data.local.pref.PreferenceManager.Companion.GU
import com.uhi.data.local.pref.PreferenceManager.Companion.HN
import com.uhi.databinding.FragmentQuestionBinding
import com.uhi.ui.common.base.BaseFragment
import com.uhi.ui.common.languageList
import com.uhi.ui.dashboard.DashboardViewModel
import com.uhi.utils.extention.*
import com.uhi.utils.glide.GlideApp
import com.uhi.utils.glide.GlideRequests
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionsFragment : BaseFragment<FragmentQuestionBinding>() {

    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var glideRequests: GlideRequests
    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardViewModel.getQuestion()
    }

    override fun initView() {
        glideRequests = GlideApp.with(this)
        questionAdapter = QuestionAdapter(arrayListOf(), glideRequests, this)
        binding.viewPager.adapter = questionAdapter
        binding.viewPager.isUserInputEnabled = false
    }

    override fun initListener() {
        binding.nextImageView.setOnClickListener(this)
        binding.previousImageView.setOnClickListener(this)
        binding.languageImageView.setOnClickListener(this)
    }

    override fun initObserver() {
        observeNotNull(dashboardViewModel.apiState) {
            it.handleListApiView(binding.progressLayout, onClickListener = {
                dashboardViewModel.getQuestion()
            }) {
                questionAdapter.add(it)
                binding.viewPager.setCurrentItem(questionAdapter.getItemsList().lastIndex, false)
            }
        }
        observeNotNull(dashboardViewModel.resultApiState) { apiResponse ->
            apiResponse.handleApiView(binding.progressLayout, onClickListener = {
                dashboardViewModel.getQuestion()
            }) { map ->
                val question = questionAdapter.getSingleItem(binding.viewPager.currentItem)
                if (map?.isEmpty() == true) {
                    dashboardViewModel.getQuestion(question?.id, question?.answer, true)
                } else {
                    val stringBuilder = StringBuilder()
                    var index = 0
                    map?.forEach {
                        index += 1
                        stringBuilder.append("$index. ${it.key}: ${it.value}")
                        stringBuilder.append("\n")
                    }
                    context?.alertDialog {
                        setTitle(R.string.title_attention)
                        setMessage(stringBuilder.toString())
                        setCancelable(false)
                        setPositiveButton(R.string.button_continue) { dialogInterface, i ->
                            dashboardViewModel.getQuestion(question?.id, question?.answer, true)
                        }
                        setNegativeButton(R.string.button_go_to) { dialogInterface, i ->

                        }
                    }?.show()
                }
            }
        }
    }

    override fun onClick(view: View?) {
        super.onClick(view)
        when (view?.id) {
            R.id.nextImageView -> {
                val question = questionAdapter.getSingleItem(binding.viewPager.currentItem)
                if (question?.answer.isNullOrEmpty()) {
                    showSnackBar(binding.progressLayout, getString(R.string.error_msg_select_answer))
                } else {
                    dashboardViewModel.getResult(questionAdapter.getItemsList())
//                    questionViewModel.getQuestion(question?.id, question?.answer, true)
                }
            }
            R.id.previousImageView -> {
                if (binding.viewPager.currentItem >= 1) {
                    val position = binding.viewPager.currentItem - 1
                    binding.viewPager.setCurrentItem(position, false)
                    questionAdapter.getItemsList().removeAt(binding.viewPager.currentItem + 1)
                } else {
                    dashboardViewModel.previousClassifications.clear()
                    requireActivity().onBackPressed()
                }
            }
            R.id.languageImageView -> {
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
            }
        }
    }
}