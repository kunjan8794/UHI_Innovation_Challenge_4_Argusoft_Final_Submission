package com.uhi.ui.common.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class TriagingResults(
    var disease: String? = null,
    var code: String? = null,
    var symptoms: List<String>? = null,
    var suggestions: List<String>? = null,
) : Parcelable