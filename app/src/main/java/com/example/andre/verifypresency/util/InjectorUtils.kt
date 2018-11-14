package com.example.andre.verifypresency.util

import com.example.andre.verifypresency.source.remote.RemoteDataBase
import com.example.andre.verifypresency.source.remote.login.LoginRepository
import com.example.andre.verifypresency.source.remote.register.RegisterRepository

object InjectorUtils {

    fun provideRegisterRepository(): RegisterRepository {

        return RegisterRepository.getInstance(RemoteDataBase.getInstance().registerRemoteDataSource)
    }

    fun provideLoginRepository(): LoginRepository{
        return LoginRepository.getInstance(RemoteDataBase.getInstance().loginRemoteDataSource)
    }
}