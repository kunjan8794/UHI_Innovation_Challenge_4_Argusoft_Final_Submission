package com.uhi.ui.dashboard.triaging

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.uhi.R
import com.uhi.databinding.FragmentTriagingBinding
import com.uhi.ui.common.INTENT_EXTRA_CODE
import com.uhi.ui.common.INTENT_EXTRA_IS_MEDICINE
import com.uhi.ui.common.INTENT_EXTRA_QUESTIONS
import com.uhi.ui.common.INTENT_EXTRA_TRIAGING
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
        binding.headerLayout.toolbar.setTitle(R.string.select_triaging)
        requireArguments().getParcelableArrayList<Question?>(INTENT_EXTRA_QUESTIONS)?.let { dashboardViewModel.getResult(it) }
        glideRequests = GlideApp.with(this)
        triagingAdapter = TriagingAdapter(arrayListOf(), this)
        binding.progressLayout.recyclerView = binding.recyclerView
        binding.recyclerView.adapter = triagingAdapter
    }

    override fun initListener() {
        binding.headerLayout.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun initObserver() {
        observeNotNull(dashboardViewModel.resultApiState) { apiResponse ->
            apiResponse.handleListApiView(binding.progressLayout, msgResId = getString(R.string.no_found_traiging), onClickListener = {
                requireArguments().getParcelableArrayList<Question?>(INTENT_EXTRA_QUESTIONS)?.let { dashboardViewModel.getResult(it) }
            }) {
                it?.let { it1 -> triagingAdapter.addAll(it1) }
            }
        }

    }

    override fun onClick(view: View?) {
        super.onClick(view)
        when (view?.id) {
            R.id.medicineImageview -> {
                navigate(R.id.action_triagingFragment_to_triagingReportFragment) {
                    putBoolean(INTENT_EXTRA_IS_MEDICINE, true)
                    putString(INTENT_EXTRA_CODE, triagingAdapter.getItemsList()[view.tag as Int]?.code)
                    putParcelable(INTENT_EXTRA_TRIAGING, triagingAdapter.getItemsList()[view.tag as Int])
                    putParcelableArrayList(INTENT_EXTRA_QUESTIONS, requireArguments().getParcelableArrayList<Question?>(INTENT_EXTRA_QUESTIONS))
                }
            }
            R.id.testReportImageView -> {
                navigate(R.id.action_triagingFragment_to_triagingReportFragment) {
                    putBoolean(INTENT_EXTRA_IS_MEDICINE, false)
                    putString(INTENT_EXTRA_CODE, triagingAdapter.getItemsList()[view.tag as Int]?.code)
                    putParcelable(INTENT_EXTRA_TRIAGING, triagingAdapter.getItemsList()[view.tag as Int])
                    putParcelableArrayList(INTENT_EXTRA_QUESTIONS, requireArguments().getParcelableArrayList<Question?>(INTENT_EXTRA_QUESTIONS))
                }
            }
        }
    }
}