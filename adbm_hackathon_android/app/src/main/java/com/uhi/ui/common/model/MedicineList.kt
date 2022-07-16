package com.uhi.ui.common.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MedicineList(
    var name: String? = null,
    var dose: String? = null,
    var code: String? = null
)