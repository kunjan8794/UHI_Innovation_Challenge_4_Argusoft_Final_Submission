package com.uhi.ui.dashboard.quetion

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.navigation.navGraphViewModels
import com.uhi.R
import com.uhi.data.local.pref.PreferenceManager.Companion.EN
import com.uhi.data.local.pref.PreferenceManager.Companion.GU
import com.uhi.data.local.pref.PreferenceManager.Companion.HN
import com.uhi.databinding.FragmentDashboardBinding
import com.uhi.databinding.FragmentQuetionsBinding
import com.uhi.ui.common.base.BaseFragment
import com.uhi.ui.common.languageList
import com.uhi.ui.common.model.Question
import com.uhi.utils.extention.*
import com.uhi.utils.glide.GlideApp
import com.uhi.utils.glide.GlideRequests
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionsFragment : BaseFragment<FragmentQuetionsBinding>() {

    private lateinit var questionAdapter: QuestionAdapter
    private lateinit var glideRequests: GlideRequests
    private val questionViewModel: QuestionViewModel by viewModels()

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
        observeNotNull(questionViewModel.apiState) {
            it.handleListApiView(binding.progressLayout, onClickListener = {
                questionViewModel.getQuestion()
            }) {
                questionAdapter.add(it)
                binding.viewPager.setCurrentItem(questionAdapter.getItemsList().lastIndex, false)
            }
        }
        observeNotNull(questionViewModel.resultApiState) { apiResponse ->
            apiResponse.handleApiView(binding.progressLayout, onClickListener = {
                questionViewModel.getQuestion()
            }) { map ->
                val question = questionAdapter.getSingleItem(binding.viewPager.currentItem)
                if (map?.isEmpty() == true) {
                    questionViewModel.getQuestion(question?.id, question?.answer, true)
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
                            questionViewModel.getQuestion(question?.id, question?.answer, true)
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
                    questionViewModel.getResult(questionAdapter.getItemsList())
//                    questionViewModel.getQuestion(question?.id, question?.answer, true)
                }
            }
            R.id.previousImageView -> {
                if (binding.viewPager.currentItem >= 1) {
                    val position = binding.viewPager.currentItem - 1
                    binding.viewPager.setCurrentItem(position, false)
                    questionAdapter.getItemsList().removeAt(binding.viewPager.currentItem + 1)
                } else {
                    questionViewModel.previousClassifications.clear()
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