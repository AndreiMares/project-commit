package com.example.andre.verifypresency.source.remote.event

import android.arch.lifecycle.MutableLiveData
import com.example.andre.verifypresency.source.models.Event
import com.example.andre.verifypresency.register.form.User

class EventRepository(
        private val eventRemoteDateSource: EventRemoteDataSource) {

    fun loadEvents(callback: EventDataSource.LoadEventsCallback) {
        this.eventRemoteDateSource.loadEventList(object : EventDataSource.LoadEventsCallback {

            override fun onEventsLoaded(users: MutableLiveData<Event>) {
                callback.onEventsLoaded(users)
            }

            override fun onDataNotAvailable() {

                callback.onDataNotAvailable()
            }

        })
    }

    fun loadEvent(eventId: String, callback: EventDataSource.LoadEventCallback) {

        this.eventRemoteDateSource.loadEvent(eventId, object : EventDataSource.LoadEventCallback {
            override fun onEventLoaded(users: User) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataNotAvailable() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }

    fun saveEvent(event: Event, callback: EventDataSource.SaveEventCallback) {

        this.eventRemoteDateSource.saveEvent(event, object : EventDataSource.SaveEventCallback {
            override fun onEventSaved(message: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onEventFailed(message: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }

    companion object {
        @Volatile
        private var INSTANCE: EventRepository? = null

        fun getInstance(eventRemoteDateSource: EventRemoteDataSource): EventRepository =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: EventRepository(eventRemoteDateSource).also { INSTANCE = it }
                }

    }
}