package com.example.andre.verifypresency.source.remote.event

import android.arch.lifecycle.MutableLiveData
import com.example.andre.verifypresency.main.form.Member
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

    fun saveEvent(event: Event, callBack: EventDataSource.SaveCallback) {

        val eventDetail = HashMap<String, Any>()

        val userId = FirebaseAuth.getInstance().currentUser?.uid

        eventDetail.put("UserId", userId!!)
        event.name.let { eventDetail.put("Name", it) }
        event.location.let { eventDetail.put("Location", it) }
//        event.memberList.let {
//
//            val memberDetail = HashMap<String, Any>()
//            it.forEach { member->
//                memberDetail.put("UserId", userId)
//                member.name.let { memberDetail.put("Name", it) }
//                member.email.let { memberDetail.put("Email", it) }
//                member.phoneNumber.let { memberDetail.put("PhoneNumber", it) }
//            }
//
//
//        }
//        val list = arrayListOf<Member>()
//        event.memberList.forEach {
//
//            list.add(it)
//        }
//
//        eventDetail.put("Members", list)


        val docReference = FirebaseFirestore.getInstance()
                .collection("Events")
                .document()


        var id = docReference.id

        docReference.set(eventDetail)
                .addOnCompleteListener { task ->
                    when {
                        task.isSuccessful -> {

                            val db = FirebaseFirestore.getInstance()
                                    .collection("Events")
                                    .document(id)
                                    .collection("Members")

                            event.memberList.forEach {

                                val memberDetail = HashMap<String, Any>()

                                FirebaseAuth.getInstance().currentUser?.let { memberDetail.put("UserId", it.uid) }
                                it.name.let { memberDetail.put("Name", it) }
                                it.email.let { memberDetail.put("Email", it) }
                                it.phoneNumber.let { memberDetail.put("PhoneNumber", it) }
                                it.active.let { memberDetail.put("Active", true) }

                                db.add(memberDetail)
                            }

                            callBack.onSuccess("")
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    exception.message?.let { callBack.onError(it) }
                }


    }
}