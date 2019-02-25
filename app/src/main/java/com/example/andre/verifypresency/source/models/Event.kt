package com.example.andre.verifypresency.source.models

import com.google.firebase.firestore.PropertyName
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

    constructor() : this(Date(), "", "")
}