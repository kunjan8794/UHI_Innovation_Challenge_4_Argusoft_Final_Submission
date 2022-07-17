package com.uhi.ui.dashboard.timeseries

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.uhi.R
import com.uhi.databinding.CustomChartComponentBinding
import com.uhi.ui.common.base.BaseAdapter
import com.uhi.ui.common.labParameterUnitMap
import com.uhi.ui.common.labParameterValueMap
import com.uhi.ui.common.model.LabTest
import com.uhi.utils.extention.toBinding
import java.text.SimpleDateFormat
import java.util.*


class PatientReportAdapter(
    private val list: ArrayList<LabTest?> = arrayListOf(),
    private val context: Context,
    private val patientId: Int
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
            binding.mainSlider.valueFrom = binding.rangeSlider.valueFrom
            binding.mainSlider.valueTo = binding.rangeSlider.valueTo
            binding.mainSlider.value = mainValue

            val mapIndexed = sortedList?.mapIndexed { index, labTestData ->
                Entry(
                    index.toFloat(),
                    labTestData.value!!,
                    AppCompatResources.getDrawable(binding.root.context, R.drawable.ic_arrow_down)
                )
            }
            val lineDataSet = LineDataSet(mapIndexed, question.title)

            binding.graph.axisRight.setDrawLabels(false)
            binding.graph.axisLeft.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)

            binding.chartLegendCustom.text =
                "Normal Range : " + labParameterValueMap.get(question.title)?.first?.toFloat() + "-" + labParameterValueMap.get(
                    question.title
                )?.second?.toFloat()

            val xAxis: XAxis = binding.graph.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            question.labTestData?.size?.let { xAxis.setLabelCount(it, true) }
            xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return sortedList?.getOrNull(value.toInt())?.date.convertDate()
                }
            }

            binding.graph.setTouchEnabled(true)
            // create marker to display box when values are selected

            // create marker to display box when values are selected
            val mv = MyMarkerView(binding.root.context, R.layout.custom_marker_view)
            mv.chartView = binding.graph
            binding.graph.marker = mv

            binding.graph.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    val highlight =
                        arrayOfNulls<Highlight>(binding.graph.getData().getDataSets().size)
                    for (j in 0 until binding.graph.getData().getDataSets().size) {
                        val iDataSet: IDataSet<*> = binding.graph.getData().getDataSets().get(j)
                        for (i in (iDataSet as LineDataSet).values.indices) {
                            if (iDataSet.values[i].x == e!!.x) {
                                highlight[j] = Highlight(e.x, e.y, j)
                            }
                        }
                    }
                    binding.graph.highlightValues(highlight)
                }

                override fun onNothingSelected() {
                }
            })


            val l: Legend = binding.graph.legend

            // modify the legend ...

            // modify the legend ...
            l.form = LegendForm.SQUARE
            l.textSize = 11f
            l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
            l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
            l.orientation = Legend.LegendOrientation.HORIZONTAL
            l.setDrawInside(false)
            l.isWordWrapEnabled = true

            if (patientId == 2 && question.title.equals("VLDL Cholesterol")) {
                binding.extraTextInChart.text =
                    "** Based on historical records, the patient had daily dose of Fluvastatin tablets from 3rd November to 17th of February.";
                binding.extraTextInChart.isVisible = true
            } else if (patientId == 1 && question.title.equals("VLDL Cholesterol")) {
                binding.extraTextInChart.text =
                    "** Based on historical records, the patient has daily dose of Fluvastatin tablets starting from 17th January to current date."
                binding.extraTextInChart.isVisible = true
            }

            binding.graph.data = LineData(listOf<ILineDataSet>(lineDataSet))
            binding.graphClickLayout.setOnClickListener {
                binding.graphView.isVisible = !binding.graphView.isVisible
                binding.showHideGraph.setImageResource(if (binding.graphView.isVisible) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down)
            }
        }
    }
}

private fun Date?.convertDate(): String {
    val sourceFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
    return sourceFormat.format(this)
}


