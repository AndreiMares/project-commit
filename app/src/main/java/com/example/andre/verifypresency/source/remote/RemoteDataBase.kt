package com.example.andre.verifypresency.source.remote

import com.example.andre.verifypresency.login.remote.LoginRemoteDataSource
import com.example.andre.verifypresency.main.remote.MemberRemoteDataSource
import com.example.andre.verifypresency.register.remote.RegisterRemoteDataSource
import com.example.andre.verifypresency.source.remote.event.EventRemoteDataSource

class RemoteDataBase private constructor() {

    var registerRemoteDataSource: RegisterRemoteDataSource = RegisterRemoteDataSource()
        private set

    var loginRemoteDataSource: LoginRemoteDataSource = LoginRemoteDataSource()
        private set

    var memberRemoteDataSource: MemberRemoteDataSource = MemberRemoteDataSource()
        private set

    var eventRemoteDataSource: EventRemoteDataSource = EventRemoteDataSource()
        private set

    companion object {
        @Volatile
        private var INSTANCE: RemoteDataBase? = null

        fun getInstance(): RemoteDataBase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: RemoteDataBase().also { INSTANCE = it }
                }


    }
}