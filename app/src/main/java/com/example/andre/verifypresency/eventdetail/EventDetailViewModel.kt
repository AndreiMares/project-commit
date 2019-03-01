package com.example.andre.verifypresency.eventdetail

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.example.andre.verifypresency.SingleLiveEvent
import com.example.andre.verifypresency.main.form.Member
import com.example.andre.verifypresency.main.remote.MemberDataSource
import com.example.andre.verifypresency.main.remote.MemberRepository
import com.example.andre.verifypresency.source.remote.event.EventRepository

class EventDetailViewModel(private val eventRepository: EventRepository,
                           private val memberRepository: MemberRepository)
    : ViewModel() {

    //observable views
    val memberList: ObservableList<Member> = ObservableArrayList()
    val selectedMemberList: ObservableList<Member> = ObservableArrayList()

    val onDataLoading = ObservableBoolean(false)
    val onEmpty = ObservableBoolean(false)
    val onDataLoadingError = ObservableBoolean(false)
    val bottomSheetBehaviorState = SingleLiveEvent<Void>()
    val navigation = SingleLiveEvent<Unit>()

    fun prepareLoadingList(): Unit = this.loadMemberList()

    fun prepareSelectedMembersList() = this.loadSelectedList()

    fun filter() = bottomSheetBehaviorState.call()

    fun sendSelectedList() {

        //build the new list
        val selectedMembers = arrayListOf<Member>()
        this.memberList.forEach {

            if (it.selected.get() && !selectedMembers.contains(it))
                selectedMembers.add(it)

        }

        with(this.selectedMemberList)
        {
            addAll(selectedMembers)
            onEmpty.set(isEmpty())

            memberList.clear()
            navigation.call()
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

    private fun loadSelectedList() =
            with(this.selectedMemberList) {
                onEmpty.set(isEmpty())
            }
}

