package com.example.andre.verifypresency.main

import android.support.v7.widget.RecyclerView
import com.example.andre.verifypresency.main.form.Member
import  com.example.andre.verifypresency.databinding.RowMemberListItemBinding

class MemberListViewHolder(private val viewBinding: RowMemberListItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {


    fun bind(member: Member) {
        this.viewBinding.model = member
        this.viewBinding.executePendingBindings()
    }
}