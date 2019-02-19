package com.example.andre.verifypresency.util

import com.example.andre.verifypresency.source.remote.RemoteDataBase
import com.example.andre.verifypresency.login.LoginRepository
import com.example.andre.verifypresency.source.remote.member.MemberRepository
import com.example.andre.verifypresency.register.RegisterRepository

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
}