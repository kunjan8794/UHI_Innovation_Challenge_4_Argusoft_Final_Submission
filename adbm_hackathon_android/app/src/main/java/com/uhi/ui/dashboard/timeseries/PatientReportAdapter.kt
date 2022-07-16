package com.uhi.ui.dashboard.timeseries

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.uhi.R
import com.uhi.databinding.CustomChartComponentBinding
import com.uhi.ui.common.base.BaseAdapter
import com.uhi.ui.common.labParameterUnitMap
import com.uhi.ui.common.labParameterValueMap
import com.uhi.ui.common.model.LabTest
import com.uhi.utils.extention.toBinding
import com.uhi.utils.glide.GlideRequests


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

    inner class ViewHolder(val binding: CustomChartComponentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
        }

        fun bind(question: LabTest) = with(question) {
            val sortedList: List<LabTest.LabTestData>? = question.labTestData?.sortedBy { it.date }
            val mainValue = sortedList?.get(sortedList.size - 1)?.value ?: 0f
            binding.diseaseTitle.text = question.title
            binding.diseaseUnit.text = labParameterUnitMap[question.title]
            binding.diseaseValue.text = mainValue.toString()
            binding.mainSlider.value = mainValue

            if (mainValue <= labParameterValueMap.get(question.title)?.first!!) {
                //Value is low
                binding.rangeSlider.valueFrom = mainValue - 2
                binding.rangeSlider.valueTo =
                    labParameterValueMap.get(question.title)?.second?.toFloat()!!
                binding.upDownArrow.setImageResource(R.drawable.arrow_down)
                binding.diseaseValue.setTextColor(Color.RED)
            } else if (mainValue >= labParameterValueMap.get(question.title)?.second!!) {
                //Value is High
                binding.rangeSlider.valueFrom =
                    labParameterValueMap.get(question.title)?.first?.toFloat()!!
                binding.rangeSlider.valueTo = mainValue + 2
                binding.upDownArrow.setImageResource(R.drawable.arrow_up)
                binding.diseaseValue.setTextColor(Color.RED)
            } else {
                //Value is in range
                binding.rangeSlider.valueFrom =
                    labParameterValueMap.get(question.title)?.first?.toFloat()!!
                binding.rangeSlider.valueTo =
                    labParameterValueMap.get(question.title)?.second?.toFloat()!!
                binding.upDownArrow.visibility = View.INVISIBLE
                binding.diseaseValue.setTextColor(Color.GREEN)
            }

            binding.rangeSlider.values = listOf(
                labParameterValueMap.get(question.title)?.first?.toFloat(),
                labParameterValueMap.get(question.title)?.second?.toFloat()
            )

            val mapIndexed = question.labTestData?.mapIndexed { index, labTestData ->
                Entry(
                    index.toFloat(),
                    labTestData.value!!
                )
            }

            val lineDataSet = LineDataSet(mapIndexed, "Data 1")

            binding.graph.data = LineData(listOf<ILineDataSet>(lineDataSet))
            binding.graphClickLayout.setOnClickListener {
                binding.graphView.isVisible = !binding.graphView.isVisible
                binding.showHideGraph.setImageResource(if (binding.graphView.isVisible) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down)
            }
        }
    }
}


