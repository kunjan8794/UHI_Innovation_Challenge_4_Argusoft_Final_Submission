package com.uhi.ui.dashboard.timeseries

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.uhi.BuildConfig
import com.uhi.R
import com.uhi.databinding.AnswerRadioButtonBinding
import com.uhi.databinding.CustomChartComponentBinding
import com.uhi.databinding.ListItemQuestionBinding
import com.uhi.databinding.ListItemTriagingBinding
import com.uhi.ui.common.base.BaseAdapter
import com.uhi.ui.common.model.LabTest
import com.uhi.ui.common.model.Question
import com.uhi.utils.extention.*
import com.uhi.utils.glide.GlideRequests
import com.uhi.utils.glide.loadUrl


class PatientReportAdapter(
    private val list: ArrayList<LabTest?> = arrayListOf(),
    private val glideRequests: GlideRequests,
    private val onClickListener: View.OnClickListener
) : BaseAdapter<LabTest>(list) {

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

    fun add(question: LabTest?) {
        list.add(question)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: CustomChartComponentBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
        }

        fun bind(question: LabTest) = with(question) {
        }
    }
}


