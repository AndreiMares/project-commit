package com.example.andre.verifypresency.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.andre.verifypresency.main.MemberViewModel
import com.example.andre.verifypresency.main.remote.MemberRepository
import com.example.andre.verifypresency.util.InjectorUtils

class MemberDetailModelFactory(private val memberRepository: MemberRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            with(modelClass) {
                when {
                    isAssignableFrom(MemberDetailViewModel::class.java) ->
                        MemberDetailViewModel(memberRepository)

                    isAssignableFrom(MemberViewModel::class.java) ->
                        MemberViewModel(memberRepository)

                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T


    companion object {

        @Volatile
        private var INSTANCE: MemberDetailModelFactory? = null

        fun getInstance(): MemberDetailModelFactory =
                INSTANCE
                        ?: synchronized(MemberDetailModelFactory::class.java) {
                            INSTANCE
                                    ?: MemberDetailModelFactory(
                                            InjectorUtils.provideMemberDetailRepository())
                                            .also { INSTANCE = it }
                        }


    }
}