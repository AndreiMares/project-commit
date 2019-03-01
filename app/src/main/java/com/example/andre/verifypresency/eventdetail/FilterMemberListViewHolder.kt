package com.example.andre.verifypresency.eventdetail

import android.support.v7.widget.RecyclerView
import com.example.andre.verifypresency.main.form.Member
import com.example.andre.verifypresency.databinding.RowFilterMemberListItemBinding

class FilterMemberListViewHolder(private val viewBinding: RowFilterMemberListItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {


    fun bind(member: Member) {
        this.viewBinding.model = member
        this.viewBinding.executePendingBindings()
    }
}