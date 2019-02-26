package com.example.andre.verifypresency.main

import android.arch.lifecycle.ViewModel
import com.example.andre.verifypresency.AppModuleEnum
import com.example.andre.verifypresency.SingleLiveEvent
import com.example.andre.verifypresency.source.remote.event.EventRepository

class CalendarViewModel(private val eventRepository: EventRepository)
    : ViewModel() {

    val navigation = SingleLiveEvent<AppModuleEnum>()


    fun onButtonClicked(appModuleEnum: AppModuleEnum): Unit =
            when (appModuleEnum) {

                AppModuleEnum.EVENTLIST ->
                    navigation.value = AppModuleEnum.EVENTLIST

                AppModuleEnum.NEWEVENT ->
                    navigation.value = AppModuleEnum.NEWEVENT

                else -> {

                }
            }
}