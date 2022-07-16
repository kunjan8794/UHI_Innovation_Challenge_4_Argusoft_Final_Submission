package com.uhi.ui.dashboard.triaging.report

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.uhi.databinding.ListItemTriagingBinding
import com.uhi.databinding.ListItemTriagingReportBinding
import com.uhi.ui.common.base.BaseAdapter
import com.uhi.ui.common.model.MedicineList
import com.uhi.ui.common.model.Question
import com.uhi.ui.common.model.TriagingResults
import com.uhi.utils.extention.*
import com.uhi.utils.glide.GlideRequests


class TriagingReportAdapter(
    private val list: ArrayList<MedicineList?> = arrayListOf()
) : BaseAdapter<MedicineList>(list) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType != LIST_ITEM_PROGRESS) {
            return ViewHolder(parent.toBinding())
        }
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        when (holder) {
            is ViewHolder -> {
                list[position]?.let { holder.bind(it) }
            }
        }
    }

    inner class ViewHolder(val binding: ListItemTriagingReportBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(triagingResults: MedicineList) = with(triagingResults) {
            binding.titleTextView.text = name
            binding.doseTextView.text = dose
            binding.codeTextView.text = "SNOMED CT: $code"
            binding.codeTextView.isVisible = code?.isNotEmpty() == true
            binding.noteTextView.text = "Note: Based on evidences, this medicine has been found effective in 37% of the cases."
            binding.doseTextView.isVisible = dose?.isNotEmpty() == true
            binding.noteTextView.isVisible = dose?.isNotEmpty() == true && bindingAdapterPosition==0
        }
    }
}

