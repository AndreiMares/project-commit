package com.example.andre.verifypresency.main

import android.arch.lifecycle.ViewModel
import com.example.andre.verifypresency.source.remote.event.EventRepository
import com.example.andre.verifypresency.source.remote.register.RegisterRepository

class EventViewModel(private val eventRepository: EventRepository)
    : ViewModel() {

}