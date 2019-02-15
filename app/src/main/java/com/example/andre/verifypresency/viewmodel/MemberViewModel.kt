package com.example.andre.verifypresency.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.example.andre.verifypresency.source.models.Member
import com.example.andre.verifypresency.source.remote.member.MemberRepository

class MemberViewModel(private val memberRepository: MemberRepository)
    : ViewModel() {


    //observable views
    val memberList: ObservableList<Member> = ObservableArrayList()
    val onDataLoading = ObservableBoolean(false)
    val onEmpty = ObservableBoolean(false)
    val onDataLoadingError = ObservableBoolean(false)
}