package com.uhi.utils.extention

import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.uhi.databinding.AnswerDropDownBinding
import com.uhi.databinding.AnswerMultiSelectionBinding
import com.uhi.databinding.AnswerRadioButtonBinding
import com.uhi.databinding.AnswerTextFieldBinding
import com.uhi.ui.common.model.Question


fun View.generateAnswerLayout(question: Question): View? {
    return when (question.type) {
        "RB" -> radioLayout(question)
        "TB" -> textFieldLayout(question)
        "CB" -> dropDownLayout(question)
        "MS" -> multiSelectionLayout(question)
        else -> null
    }
}

fun View.radioLayout(question: Question): View {
    val binding = AnswerRadioButtonBinding.inflate(LayoutInflater.from(this.context))
    binding.positiveRadioButton.text = question.options?.values?.toList()?.get(0)
    binding.positiveRadioButton.isChecked = question.options?.getKey(binding.positiveRadioButton.text).equals(question.answer)
    binding.negativeRadioButton.text = question.options?.values?.toList()?.get(1)
    binding.negativeRadioButton.isChecked = question.options?.getKey(binding.negativeRadioButton.text).equals(question.answer)
    binding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
        question.answer = question.options.getKey(findViewById<RadioButton>(i).text.toString())
    }
    return binding.root
}


fun View.textFieldLayout(question: Question): View {
    val binding = AnswerTextFieldBinding.inflate(LayoutInflater.from(this.context))
    binding.textInputEditText.setText(question.answer)
    binding.textInputEditText.onTextChanged {
        question.answer = it
    }
    return binding.root
}

fun View.dropDownLayout(question: Question): View {
    val binding = AnswerDropDownBinding.inflate(LayoutInflater.from(this.context))
    val list= question.options?.values?.toTypedArray()
    list?.let {
        binding.autoCompleteTextView.setSimpleItems(it)
    }
    binding.autoCompleteTextView.setText(question.options?.getOrDefault(question.answer ?: "", "").toString(),false)
    binding.autoCompleteTextView.setOnItemClickListener { adapterView, view, i, l ->
        question.answer = list?.get(i)?.let { question.options.getKey(it) }
    }
    return binding.root
}

fun View.multiSelectionLayout(question: Question): View {
    val binding = AnswerMultiSelectionBinding.inflate(LayoutInflater.from(this.context))
    val list = ArrayList<String>()
    question.options?.values?.forEach {
        val checkBox = CheckBox(context)
        checkBox.text = it
        checkBox.isChecked = question.options.getKey(it)?.let { it1 -> question.answer?.contains(it1) } ?: false
        checkBox.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                if (!list.contains(question.options.getKey(it)))
                    question.options.getKey(it)?.let { it1 -> list.add(it1) }
            } else {
                list.remove(question.options.getKey(it))
            }
            question.answer = list.joinToString()
        }
        binding.rootLayout.addView(checkBox)
    }
    return binding.root
}

fun <K, V> Map<K, V>?.getKey(value: V): K? {
    return this?.filterValues { it?.equals(value) == true }?.keys?.first()
}
