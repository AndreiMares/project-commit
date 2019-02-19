package com.example.andre.verifypresency.source.remote

import com.example.andre.verifypresency.login.LoginRemoteDataSource
import com.example.andre.verifypresency.source.remote.member.MemberRemoteDataSource
import com.example.andre.verifypresency.register.RegisterRemoteDataSource

class RemoteDataBase private constructor() {

    var registerRemoteDataSource: RegisterRemoteDataSource = RegisterRemoteDataSource()
        private set

    var loginRemoteDataSource: LoginRemoteDataSource = LoginRemoteDataSource()
        private set

    var memberRemoteDataSource: MemberRemoteDataSource = MemberRemoteDataSource()
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