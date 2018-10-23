package com.example.andre.verifypresency.util

import android.databinding.BindingAdapter
import android.view.View
import android.widget.EditText

@BindingAdapter("bind:error")
fun setError(editText: EditText, strOrResId: Any?) {

    if (strOrResId != null) {
        if (strOrResId is Int) {
            editText.error = editText.context.getString(strOrResId)
        } else {
            editText.error = strOrResId as String
        }
    }
}

@BindingAdapter("bind:onFocus")
fun EditText.bindFocusChange(onFocusChangeListener: View.OnFocusChangeListener?) {

    if (onFocusChangeListener != null)
        this.onFocusChangeListener = onFocusChangeListener
}