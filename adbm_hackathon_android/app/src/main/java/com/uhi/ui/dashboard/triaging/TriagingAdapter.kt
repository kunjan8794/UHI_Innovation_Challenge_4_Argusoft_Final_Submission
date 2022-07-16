package com.uhi.ui.dashboard.triaging

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.uhi.R
import com.uhi.databinding.ListItemTriagingBinding
import com.uhi.ui.common.INTENT_EXTRA_CODE
import com.uhi.ui.common.INTENT_EXTRA_IS_MEDICINE
import com.uhi.ui.common.base.BaseAdapter
import com.uhi.ui.common.model.TriagingResults
import com.uhi.utils.extention.navigate
import com.uhi.utils.extention.toBinding


class TriagingAdapter(
    private val list: ArrayList<TriagingResults?> = arrayListOf()
) : BaseAdapter<TriagingResults>(list) {

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

    inner class ViewHolder(val binding: ListItemTriagingBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.medicineImageview.setOnClickListener {
                it.navigate(R.id.action_triagingFragment_to_triagingReportFragment) {
                    putBoolean(INTENT_EXTRA_IS_MEDICINE, true)
                    putString(INTENT_EXTRA_CODE, list[bindingAdapterPosition]?.code)
                }
            }
            binding.testReportImageView.setOnClickListener {
                it.navigate(R.id.action_triagingFragment_to_triagingReportFragment) {
                    putBoolean(INTENT_EXTRA_IS_MEDICINE, false)
                    putString(INTENT_EXTRA_CODE, list[bindingAdapterPosition]?.code)
                }
            }
        }

        fun bind(triagingResults: TriagingResults) = with(triagingResults) {
            binding.titleTextView.text = disease
            binding.symptomsTextView.text = symptoms?.getString()
            binding.suggestionsGroup.isVisible = suggestions?.isNotEmpty() == true
            binding.suggestionsTextView.text = suggestions?.getString()
        }
    }
}

private fun <E> List<E>?.getString(): String {
    val stringBuilder = StringBuilder()
    this?.forEachIndexed { index, e ->
        stringBuilder.append("\u2022 $e")
        if (index != lastIndex)
            stringBuilder.append("\n")
    }
    return stringBuilder.toString()
}

