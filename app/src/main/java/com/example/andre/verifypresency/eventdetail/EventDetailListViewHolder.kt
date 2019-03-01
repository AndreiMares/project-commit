package com.example.andre.verifypresency.eventdetail

import android.support.v7.widget.RecyclerView
import com.example.andre.verifypresency.databinding.RowEventDetailListItemBinding
import com.example.andre.verifypresency.main.form.Member

class EventDetailListViewHolder(private val viewBinding: RowEventDetailListItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {


    fun bind(member: Member) {
        this.viewBinding.model = member
        this.viewBinding.executePendingBindings()
    }
}