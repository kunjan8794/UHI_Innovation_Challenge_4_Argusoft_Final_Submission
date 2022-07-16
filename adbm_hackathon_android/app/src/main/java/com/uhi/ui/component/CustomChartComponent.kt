package com.uhi.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.uhi.R

class CustomChartComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.custom_chart_component, this, true)
        orientation = HORIZONTAL

        attrs?.let {
            val typedArray =
                context.obtainStyledAttributes(it, R.styleable.CustomChartComponent, 0, 0)

            val diseaseTitle = findViewById<TextView>(R.id.disease_title)
            val diseaseValue = findViewById<TextView>(R.id.disease_value)
            val diseaseUnit = findViewById<TextView>(R.id.disease_unit)

            try {
                diseaseTitle.text =
                    typedArray.getString(R.styleable.CustomChartComponent_diseaseTitle)
                diseaseValue.text =
                    typedArray.getString(R.styleable.CustomChartComponent_diseaseValue)
                diseaseUnit.text =
                    typedArray.getString(R.styleable.CustomChartComponent_diseaseUnit)
            } finally {
                typedArray.recycle()
            }
        }
    }
}