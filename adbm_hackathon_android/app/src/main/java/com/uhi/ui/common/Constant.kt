package com.uhi.ui.common

const val INTENT_EXTRA_PATIENT_ID = "INTENT_EXTRA_PATIENT_ID"
const val MY_UPDATE_REQUEST_CODE = 50

val languageList = arrayOf("English", "Hindi", "Gujarati")

val labParameterUnitMap = mapOf(
    "LDL/HDL Ratio" to "Ratio",
    "VLDL Cholesterol" to "mg/dl",
    "Non HDL Cholesterol" to "mg/dl",
    "Triglycerides" to "mg/dl",
    "Total Cholesterol" to "mg/dl"
)

val labParameterValueMap = mapOf(
    "LDL/HDL Ratio" to Pair(0.5, 3.0),
    "VLDL Cholesterol" to Pair(6.0, 30.0),
    "Non HDL Cholesterol" to Pair(0.0, 160.0),
    "Triglycerides" to Pair(0.0, 150.0),
    "Total Cholesterol" to Pair(0.0, 200.0)
)