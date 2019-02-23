package com.example.andre.verifypresency.source.remote.event

import android.arch.lifecycle.MutableLiveData
import com.example.andre.verifypresency.source.models.Event
import com.example.andre.verifypresency.register.form.User

interface EventDataSource {

    interface LoadEventsCallback {

        fun onEventsLoaded(users: MutableLiveData<Event>)

        fun onDataNotAvailable()
    }

    interface LoadEventCallback {

        fun onEventLoaded(users: User)

        fun onDataNotAvailable()
    }

    interface SaveEventCallback {

        fun onEventSaved(message: String)

        fun onEventFailed(message: String)
    }


    fun getEvent(userId: String, callback: LoadEventCallback)

    fun saveEvent(event: Event, callback: SaveEventCallback)
}