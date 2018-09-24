package com.example.andre.verifypresency.persistance.models

class Member:Entity() {


    var MemberId: Long = 0
    lateinit var FirstName: String
    lateinit var LastName: String
    lateinit var Email: String
    lateinit var PhoneNumber: String

}