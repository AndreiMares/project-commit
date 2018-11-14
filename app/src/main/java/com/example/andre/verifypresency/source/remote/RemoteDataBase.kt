package com.example.andre.verifypresency.source.remote

import com.example.andre.verifypresency.source.remote.login.LoginRemoteDataSource
import com.example.andre.verifypresency.source.remote.register.RegisterRemoteDataSource

class RemoteDataBase private constructor() {

    var registerRemoteDataSource: RegisterRemoteDataSource = RegisterRemoteDataSource()
        private set

    var loginRemoteDataSource: LoginRemoteDataSource = LoginRemoteDataSource()
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