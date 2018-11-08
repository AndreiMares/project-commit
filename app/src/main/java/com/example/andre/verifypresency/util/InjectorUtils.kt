package com.example.andre.verifypresency.util

import com.example.andre.verifypresency.source.remote.RemoteDataBase
import com.example.andre.verifypresency.source.remote.user.UserRepository

object InjectorUtils {

    fun provideUserRepository(): UserRepository {

        return UserRepository.getInstance(RemoteDataBase.getInstance().userRemoteDataSource)
    }
}