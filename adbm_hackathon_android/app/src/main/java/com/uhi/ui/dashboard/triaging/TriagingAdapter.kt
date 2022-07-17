package com.uhi.ui.dashboard.triaging

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.uhi.R
import com.uhi.databinding.ListItemTriagingBinding
import com.uhi.ui.common.INTENT_EXTRA_CODE
import com.uhi.ui.common.INTENT_EXTRA_IS_MEDICINE
import com.uhi.ui.common.INTENT_EXTRA_TRIAGING
import com.uhi.ui.common.base.BaseAdapter
import com.uhi.ui.common.model.TriagingResults
import com.uhi.utils.extention.navigate
import com.uhi.utils.extention.toBinding


class TriagingAdapter(
    private val list: ArrayList<TriagingResults?> = arrayListOf(),
    private val onClickListener: View.OnClickListener
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
            binding.medicineImageview.setOnClickListener (onClickListener)
            binding.testReportImageView.setOnClickListener(onClickListener)
        }

        fun bind(triagingResults: TriagingResults) = with(triagingResults) {
            binding.titleTextView.text = disease
            binding.medicineImageview.tag = bindingAdapterPosition
            binding.testReportImageView.tag = bindingAdapterPosition
            binding.symptomsTextView.text = symptoms?.getString()
            binding.suggestionsGroup.isVisible = suggestions?.isNotEmpty() == true
            binding.suggestionsTextView.text = suggestions?.getString()
        }
    }
}

fun <E> List<E>?.getString(): String {
    val stringBuilder = StringBuilder()
    this?.forEachIndexed { index, e ->
        stringBuilder.append("\u2022 $e")
        if (index != lastIndex)
            stringBuilder.append("\n")
    }
    return stringBuilder.toString()
}

