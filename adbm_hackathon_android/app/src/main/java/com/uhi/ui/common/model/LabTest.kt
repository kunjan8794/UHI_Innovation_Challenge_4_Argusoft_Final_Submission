package com.uhi.ui.common.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@JsonClass(generateAdapter = true)
data class LabTest(
    var title: String? = null,
    var labTestData: List<LabTestData>? = null,
) : Parcelable {
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class LabTestData(
        var date: String? = null,
        var value: Float? = null,
    ) : Parcelable
}