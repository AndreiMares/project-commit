package com.example.andre.verifypresency.source.remote.user

import com.example.andre.verifypresency.source.models.User

class UserRepository(
        private val usersRemoteDateSource: UserRemoteDataSource) {

    fun saveUser(user: User, callBack: UserDataSource.SaveUserCallback) {

        this.usersRemoteDateSource.saveUser(user, object : UserDataSource.SaveUserCallback {

            override fun onUserSaved(message: String) {

                callBack.onUserSaved(message)
            }

            override fun onSaveFailed(message: String) {

                callBack.onSaveFailed(message)
            }

        })
    }

    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null

        fun getInstance(usersRemoteDateSource: UserRemoteDataSource): UserRepository =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: UserRepository(usersRemoteDateSource).also { INSTANCE = it }
                }

    }
}