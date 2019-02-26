package com.example.andre.verifypresency.eventdetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.andre.verifypresency.main.remote.MemberRepository
import com.example.andre.verifypresency.source.remote.event.EventRepository
import com.example.andre.verifypresency.util.InjectorUtils

class EventDetailModelFactory(private val memberRepository: MemberRepository,
                              private val eventRepository: EventRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            with(modelClass) {
                when {
                    isAssignableFrom(EventDetailViewModel::class.java) ->
                        EventDetailViewModel(eventRepository = eventRepository, memberRepository = memberRepository)

                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T


    companion object {

        @Volatile
        private var INSTANCE: EventDetailModelFactory? = null

        fun getInstance(): EventDetailModelFactory =
                INSTANCE
                        ?: synchronized(EventDetailModelFactory::class.java) {
                            INSTANCE
                                    ?: EventDetailModelFactory(
                                            InjectorUtils.provideMemberDetailRepository(),
                                            InjectorUtils.provideEventRepository())
                                            .also { INSTANCE = it }
                        }
    }
}