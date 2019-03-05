package com.example.andre.verifypresency.source.models

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.google.firebase.firestore.PropertyName
import com.example.andre.verifypresency.main.form.Member
import java.util.*

class Event(@set:PropertyName("ScheduleTime")
            @get:PropertyName("ScheduleTime")
            var scheduleTime: Date,
            @set:PropertyName("Name")
            @get:PropertyName("Name")
            var name: String,
            @set:PropertyName("Location")
            @get:PropertyName("Location")
            var location: String) {

    var eventId: String = ""
    val memberList: ObservableList<Member> = ObservableArrayList()

    constructor() : this(Date(), "", "")
}