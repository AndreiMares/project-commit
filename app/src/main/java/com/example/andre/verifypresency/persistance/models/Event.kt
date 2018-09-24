package com.example.andre.verifypresency.persistance.models

import java.util.*

class Event: Entity() {

    var EventId: Long = 0
    lateinit var ScheduleTime: Date
    var EventDetailId: Long = 0


}