package com.uhi.ui.common

import android.os.Bundle
import android.view.View
import com.uhi.databinding.DialogImagePickerBinding
import com.uhi.ui.common.base.BaseBottomSheetDialogFragment
import com.uhi.utils.common.ImagePickerUtils

/**
 * USAGE : To open Camera and Gallery file picker via BottomSheetDialog
 * Created by R.S.
 */
class ImagePickerDialog private constructor() : BaseBottomSheetDialogFragment<DialogImagePickerBinding>() {

    companion object {
        fun newInstance(
            optionSelector: ImagePickerUtils.OptionSelect,
            hasSelectMultiple: Boolean = false
        ): ImagePickerDialog {
            val frag = ImagePickerDialog()
            frag.setImageSelector(optionSelector, hasSelectMultiple)
            return frag
        }
    }

    private var optionSelector: ImagePickerUtils.OptionSelect? = null
    private var hasSelectMultiple: Boolean = false

    private fun setImageSelector(
        optionSelector: ImagePickerUtils.OptionSelect,
        hasSelectMultiple: Boolean
    ) {
        this.optionSelector = optionSelector
        this.hasSelectMultiple = hasSelectMultiple
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvCancel.setOnClickListener { dismiss() }
        binding.tvCamera.setOnClickListener {
            // Select image from camera
            ImagePickerUtils.clearAllContext()
            optionSelector?.cameraClick()
            dismiss()
        }
        binding.tvGallery.setOnClickListener {
            // Select image from gallery
            ImagePickerUtils.clearAllContext()
            optionSelector?.galleryClick()
            dismiss()
        }
    }

    override fun initView() {
    }

    override fun initListener() {
    }

    override fun initObserver() {
    }
}
