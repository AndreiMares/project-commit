package com.example.andre.verifypresency.main

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.example.andre.verifypresency.main.form.Member
import com.example.andre.verifypresency.main.remote.MemberDataSource
import com.example.andre.verifypresency.main.remote.MemberRepository

class MemberViewModel(private val memberRepository: MemberRepository)
    : ViewModel() {

    //observable views
    val memberList: ObservableList<Member> = ObservableArrayList()
    val onDataLoading = ObservableBoolean(false)
    val onEmpty = ObservableBoolean(false)
    val onDataLoadingError = ObservableBoolean(false)

    fun prepareLoadingList(): Unit = this.loadProductList()

    private fun loadProductList() {

        this.onDataLoading.set(true)

        this.memberRepository.getMembersList(callBack = object : MemberDataSource.LoadListCallback<Member> {
            override fun onListLoaded(list: List<Member>) {
                val repoList: List<Member> = list

                onDataLoading.set(false)
                onDataLoadingError.set(false)

                with(memberList) {
                    clear()
                    addAll(repoList)
                    onEmpty.set(isEmpty())
                }
            }

            override fun onError() {

                onDataLoading.set(false)
                onDataLoadingError.set(true)

            }
        })
    }
}