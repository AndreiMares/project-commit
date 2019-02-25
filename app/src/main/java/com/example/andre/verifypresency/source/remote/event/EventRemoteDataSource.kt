package com.example.andre.verifypresency.source.remote.event

import android.arch.lifecycle.MutableLiveData
import com.example.andre.verifypresency.source.models.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EventRemoteDataSource {

    fun loadEventList(callback: EventDataSource.LoadEventsCallback) {

        val list = MutableLiveData<Event>()

        callback.onEventsLoaded(list)


    }

    fun loadEvent(eventId: String, callBack: EventDataSource.LoadEventCallback) {

    }

    fun saveEvent(event: Event, callBack: EventDataSource.SaveEventCallback) {

//        val eventDetail = HashMap<String, Any>()
//
//        FirebaseAuth.getInstance().currentUser?.let { eventDetail.put("UserId", it.uid) }
//        event.title?.let { eventDetail.put("Title", it) }
//        event.location?.let { eventDetail.put("Location", it) }
//        event.eventType?.let { eventDetail.put("EvenType", it) }
//        event.scheduleTime?.let { eventDetail.put("ScheduleTime", it) }
//
//        val db = FirebaseFirestore.getInstance()
//
//        db.collection("Events").document().set(eventDetail)
//                .addOnCompleteListener { task ->
//                    if (task.isComplete && task.isSuccessful) {
////                        this.sendVerificationEmail(callBack)
//                        callBack.onEventSaved("Event successfully saved!")
//                    } else {
//                        task.exception?.message?.let { callBack.onEventFailed(it) }
//
//                    }
//                }

    }
}