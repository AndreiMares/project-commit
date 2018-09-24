package com.example.andre.verifypresency.persistance.models

class EventDetail: Entity() {

    var EventDetailId: Long = 0
    lateinit var Title: String
    lateinit var Location: String
    lateinit var OrganizationName: String
    var DictionaryDetailId: Long = 0



}