package com.example.andre.verifypresency.util

import com.example.andre.verifypresency.source.remote.RemoteDataBase
import com.example.andre.verifypresency.login.remote.LoginRepository
import com.example.andre.verifypresency.main.remote.MemberRepository
import com.example.andre.verifypresency.register.remote.RegisterRepository
import com.example.andre.verifypresency.source.remote.event.EventRepository

object InjectorUtils {

    fun provideRegisterRepository(): RegisterRepository {

        return RegisterRepository.getInstance(RemoteDataBase.getInstance().registerRemoteDataSource)
    }

    fun provideLoginRepository(): LoginRepository {
        return LoginRepository.getInstance(RemoteDataBase.getInstance().loginRemoteDataSource)
    }

    fun provideMemberDetailRepository(): MemberRepository {
        return MemberRepository.getInstance(RemoteDataBase.getInstance().memberRemoteDataSource)
    }

    fun provideEventRepository(): EventRepository {
        return EventRepository.getInstance(RemoteDataBase.getInstance().eventRemoteDataSource)
    }
}