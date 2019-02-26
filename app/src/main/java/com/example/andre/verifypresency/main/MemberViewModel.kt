package com.example.andre.verifypresency.main

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableList
import com.example.andre.verifypresency.SingleLiveEvent
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
    val onBottomSheetBehaviorState = SingleLiveEvent<Unit>()
    var onNavigation = SingleLiveEvent<String>()

    private var selectedMember: Member? = null
    var itemPosition: ObservableField<Int> = ObservableField(-1)

    fun prepareLoadingList(): Unit = this.loadMemberList()

    fun cardViewClicked(member: Member) {
        this.selectedMember = member
        this.onBottomSheetBehaviorState.call()

    }

    fun openMemberDetailActivity() {
        this.onBottomSheetBehaviorState.call()
        this.onNavigation.value = this.selectedMember?.name
    }

    fun deleteMember() = this.deleteFromFireStore()

    private fun deleteFromFireStore() {

        this.onBottomSheetBehaviorState.call()
        this.onDataLoading.set(true)

        this.selectedMember?.let {

            val position = this.memberList.indexOf(it)

            this.memberRepository.deleteMember(it, object : MemberDataSource.DeleteCallback {
                override fun onSuccess() {
                    onDataLoading.set(false)

                    //notify binding adapter
                    itemPosition.set(position)

                    memberList.removeAt(position)
                }

                override fun onFailed(message: String?) {
                }

            })
        }

    }

    private fun loadMemberList() {

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