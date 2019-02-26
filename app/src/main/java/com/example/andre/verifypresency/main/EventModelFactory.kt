package com.example.andre.verifypresency.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.andre.verifypresency.eventlist.EventViewModel
import com.example.andre.verifypresency.source.remote.event.EventRepository
import com.example.andre.verifypresency.util.InjectorUtils

class EventModelFactory(private val eventRepository: EventRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            with(modelClass) {
                when {
                    isAssignableFrom(CalendarViewModel::class.java) ->
                        CalendarViewModel(eventRepository)

                    isAssignableFrom(EventViewModel::class.java) ->
                        EventViewModel(eventRepository)

                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {

        @Volatile
        private var INSTANCE: EventModelFactory? = null

        fun getInstance(): EventModelFactory =
                INSTANCE
                        ?: synchronized(EventModelFactory::class.java) {
                            INSTANCE
                                    ?: EventModelFactory(
                                            InjectorUtils.provideEventRepository())
                                            .also { INSTANCE = it }
                        }
    }
}