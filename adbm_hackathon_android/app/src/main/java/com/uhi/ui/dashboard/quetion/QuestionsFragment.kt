package com.uhi.ui.dashboard.quetion

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.uhi.R
import com.uhi.databinding.FragmentQuestionBinding
import com.uhi.ui.common.INTENT_EXTRA_QUESTIONS
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
        glideRequests = GlideApp.with(this)
        questionAdapter = QuestionAdapter(arrayListOf(), glideRequests, this)
    }

    override fun initView() {
        binding.headerLayout.toolbar.setTitle(R.string.label_symptoms_triaging)
        binding.headerLayout.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.viewPager.adapter = questionAdapter
        binding.viewPager.isUserInputEnabled = false
    }

    override fun initListener() {
        binding.nextImageView.setOnClickListener(this)
        binding.previousImageView.setOnClickListener(this)
        binding.languageImageView.setOnClickListener(this)
    }

    override fun initObserver() {
        observeNotNull(dashboardViewModel.apiState) { apiResponse ->
            apiResponse.handleListApiView(binding.progressLayout, onClickListener = {
                dashboardViewModel.getQuestion()
            }) {
                if (it?.id == null) {
                    navigate(R.id.action_questionsFragment_to_triagingFragment){
                        putParcelableArrayList(INTENT_EXTRA_QUESTIONS,questionAdapter.getItemsList())
                    }
                } else {
                    questionAdapter.add(it)
                    binding.viewPager.setCurrentItem(questionAdapter.getItemsList().lastIndex, false)
                }
            }
        }
        /*observeNotNull(dashboardViewModel.resultApiState) { apiResponse ->
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
        }*/
    }

    override fun onClick(view: View?) {
        super.onClick(view)
        when (view?.id) {
            R.id.nextImageView -> {
                val question = questionAdapter.getSingleItem(binding.viewPager.currentItem)
               /* if (question?.answer.isNullOrEmpty()) {
                    showSnackBar(binding.progressLayout, getString(R.string.error_msg_select_answer))
                } else {*/
//                    dashboardViewModel.getResult(questionAdapter.getItemsList())
                    dashboardViewModel.getQuestion(question?.id, question?.answer, true)
//                }
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

            }
        }
    }
}