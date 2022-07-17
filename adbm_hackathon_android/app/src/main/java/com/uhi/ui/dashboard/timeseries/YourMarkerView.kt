package com.uhi.ui.dashboard.timeseries

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.github.mikephil.charting.components.MarkerView
import android.widget.TextView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.uhi.R

class YourMarkerView(context: Context?, layoutResource: Int) : MarkerView(context, layoutResource) {
    private val tvContent: TextView

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    override fun refreshContent(e: Entry, highlight: Highlight) {
        tvContent.text = "" + e.y

        // this will perform necessary layouting
        super.refreshContent(e, highlight)
    }

    private var mOffset: MPPointF? = null
    override fun getOffset(): MPPointF {
        if (mOffset == null) {
            // center the marker horizontally and vertically
            mOffset = MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
        }
        return mOffset!!
    }

    init {
        val view = LayoutInflater.from(context).inflate(layoutResource, null)
        // find your layout components
        tvContent = view.findViewById<View>(R.id.tvContent) as TextView
    }
}