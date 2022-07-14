package com.uhi.ui.dashboard.home.detail

import com.uhi.databinding.FragmentDetailBinding
import com.uhi.ui.common.INTENT_EXTRA_ALBUM
import com.uhi.ui.common.base.BaseFragment
import com.uhi.ui.common.model.Album

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    override fun initView() {
        requireArguments().getParcelable<Album>(INTENT_EXTRA_ALBUM)?.let {
            binding.nameTextView.text = it.id.toString()
        }
    }

    override fun initListener() {
    }

    override fun initObserver() {
    }
}