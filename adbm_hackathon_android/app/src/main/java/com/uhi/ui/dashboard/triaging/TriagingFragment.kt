package com.uhi.ui.dashboard.triaging

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.uhi.databinding.FragmentTriagingBinding
import com.uhi.ui.common.INTENT_EXTRA_QUESTIONS
import com.uhi.ui.common.base.BaseFragment
import com.uhi.ui.common.model.Question
import com.uhi.ui.dashboard.DashboardViewModel
import com.uhi.utils.extention.*
import com.uhi.utils.glide.GlideApp
import com.uhi.utils.glide.GlideRequests
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TriagingFragment : BaseFragment<FragmentTriagingBinding>() {

    private lateinit var triagingAdapter: TriagingAdapter
    private lateinit var glideRequests: GlideRequests
    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initView() {
        requireArguments().getParcelableArrayList<Question?>(INTENT_EXTRA_QUESTIONS)?.let { dashboardViewModel.getResult(it) }
        glideRequests = GlideApp.with(this)
        triagingAdapter = TriagingAdapter(arrayListOf())
        binding.progressLayout.recyclerView = binding.recyclerView
        binding.recyclerView.adapter = triagingAdapter
    }

    override fun initListener() {

    }

    override fun initObserver() {
        observeNotNull(dashboardViewModel.resultApiState) { apiResponse ->
            apiResponse.handleListApiView(binding.progressLayout, onClickListener = {
                requireArguments().getParcelableArrayList<Question?>(INTENT_EXTRA_QUESTIONS)?.let { dashboardViewModel.getResult(it) }
            }) {
                it?.let { it1 -> triagingAdapter.addAll(it1) }
            }
        }

    }

    override fun onClick(view: View?) {
        super.onClick(view)
        when (view?.id) {

        }
    }
}