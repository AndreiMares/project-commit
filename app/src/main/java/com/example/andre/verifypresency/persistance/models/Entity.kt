package com.example.andre.verifypresency.persistance.models

import java.util.*

open class Entity {


    lateinit var SyncLastChnageTime: Date
    var SyncLastChangUserId: Long = 0
    var SyncStatus: Byte = 0

}