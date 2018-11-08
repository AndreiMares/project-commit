package com.example.andre.verifypresency.source.remote

import com.example.andre.verifypresency.source.remote.user.UserRemoteDataSource

class RemoteDataBase private constructor() {

    var userRemoteDataSource: UserRemoteDataSource = UserRemoteDataSource()
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