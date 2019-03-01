package com.example.andre.verifypresency.util

import android.databinding.BindingAdapter
import android.support.design.widget.TextInputLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import com.example.andre.verifypresency.eventdetail.FilterMemberListAdapter
import com.example.andre.verifypresency.main.MemberListAdapter
import com.example.andre.verifypresency.main.form.Member

//@BindingAdapter("bind:error")
//fun setError(editText: EditText, strOrResId: Any?) {
//
//    if (strOrResId != null) {
//        if (strOrResId is Int) {
//            editText.error = editText.context.getString(strOrResId)
//
//        } else {
//            editText.error = strOrResId as String
//        }
//    } else
//        editText.error = null
//}

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

@BindingAdapter("bind:memberList")
fun setMemberList(recyclerView: RecyclerView, list: List<Member>) {
    when (recyclerView.adapter) {

        is MemberListAdapter -> with(recyclerView.adapter as MemberListAdapter) {
            loadList(list)
        }

        is FilterMemberListAdapter -> with(recyclerView.adapter as FilterMemberListAdapter) {
            loadList(list)
        }
    }
}

@BindingAdapter("bind:notifyItemDeleted")
fun notifyItemDeleted(recyclerView: RecyclerView, position: Int) {

    if (position != -1) {
        when (recyclerView.adapter) {

            is MemberListAdapter -> {
                with(recyclerView.adapter as MemberListAdapter) {
                    this.notifyItemRemoved(position)
                }
            }
        }
    }
}

@BindingAdapter("bind:selectAll")
fun selectItems(view: View, items: List<Member>) {

    val checkBox = view as CheckBox

    checkBox.setOnClickListener {

        when (checkBox.isChecked) {

            true -> {
                items.forEach { t -> t.selected.set(true) }
            }

            false -> {
                items.forEach { t -> t.selected.set(false) }
            }
        }

    }


}


