package com.uhi.ui.common.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TriagingResults (
    var disease: String? = null,
    var code: String? = null,
    var symptoms: List<String>? = null,
    var suggestions: List<String>? = null,
)