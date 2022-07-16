package com.uhi.ui.common.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TriagingRequest(
    var data: Map<String, Any?>? = null,
    var classification: Map<String, Any?>? = null,
    var preferredLanguage: String? = null,
)