package com.uhi.ui.common.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Question(
    var id: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var image: String? = null,
    var question: String? = null,
    var type: String? = null,
    var options: Map<String, String>? = null,
    var answer: String? = null,
) : Parcelable