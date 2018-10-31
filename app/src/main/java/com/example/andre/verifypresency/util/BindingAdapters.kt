package com.example.andre.verifypresency.util

import android.databinding.BindingAdapter
import android.support.design.widget.TextInputLayout
import android.view.View
import android.widget.EditText
import android.widget.ScrollView

@BindingAdapter("bind:error")
fun setError(editText: EditText, strOrResId: Any?) {

    if (strOrResId != null) {
        if (strOrResId is Int) {
            editText.error = editText.context.getString(strOrResId)

        } else {
            editText.error = strOrResId as String
        }
    } else
        editText.error = null
}

@BindingAdapter("bind:errorInput")
fun setError(editText: TextInputLayout, strOrResId: Any?) {

    if (strOrResId != null) {
        if (strOrResId is Int) {
            editText.error = editText.context.getString(strOrResId)

        } else {
            editText.error = strOrResId as String
        }
    } else
        editText.isErrorEnabled = false
}

@BindingAdapter("bind:onFocus")
fun EditText.bindFocusChange(onFocusChangeListener: View.OnFocusChangeListener?) {

    if (onFocusChangeListener != null)
        this.onFocusChangeListener = onFocusChangeListener
}
