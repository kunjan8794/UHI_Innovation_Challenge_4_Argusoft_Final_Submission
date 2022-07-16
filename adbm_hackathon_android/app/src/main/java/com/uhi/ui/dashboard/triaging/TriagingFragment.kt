package com.uhi.ui.dashboard.triaging

import android.view.View
import androidx.fragment.app.viewModels
import com.uhi.R
import com.uhi.data.local.pref.PreferenceManager.Companion.EN
import com.uhi.data.local.pref.PreferenceManager.Companion.GU
import com.uhi.data.local.pref.PreferenceManager.Companion.HN
import com.uhi.databinding.FragmentTriagingBinding
import com.uhi.ui.common.base.BaseFragment
import com.uhi.ui.common.languageList
import com.uhi.ui.dashboard.quetion.QuestionAdapter
import com.uhi.ui.dashboard.quetion.QuestionViewModel
import com.uhi.utils.extention.*
import com.uhi.utils.glide.GlideApp
import com.uhi.utils.glide.GlideRequests
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TriagingFragment : BaseFragment<FragmentTriagingBinding>() {

    private lateinit var triagingAdapter: TriagingAdapter
    private lateinit var glideRequests: GlideRequests
    private val questionViewModel: QuestionViewModel by viewModels()

    override fun initView() {
        glideRequests = GlideApp.with(this)
        triagingAdapter = TriagingAdapter(arrayListOf(), glideRequests, this)
    }

    override fun initListener() {

    }

    override fun initObserver() {
        observeNotNull(questionViewModel.apiState) {
            it.handleListApiView(binding.progressLayout, onClickListener = {
                questionViewModel.getQuestion()
            }) {
                triagingAdapter.add(it)
            }
        }

    }

    override fun onClick(view: View?) {
        super.onClick(view)
        when (view?.id) {

        }
    }
}