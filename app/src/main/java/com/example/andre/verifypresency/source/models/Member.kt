package com.example.andre.verifypresency.source.models

import com.google.firebase.firestore.PropertyName

data class Member(
        @set:PropertyName("Name")
        @get:PropertyName("Name")
        var name: String,
        @set:PropertyName("Email")
        @get:PropertyName("Email")
        var email: String,
        @set:PropertyName("PhoneNumber")
        @get:PropertyName("PhoneNumber")
        var phoneNumber: String,
        @set:PropertyName("Active")
        @get:PropertyName("Active")
        var active: Boolean) : BaseEntity() {

    constructor() : this("","","", false)
}