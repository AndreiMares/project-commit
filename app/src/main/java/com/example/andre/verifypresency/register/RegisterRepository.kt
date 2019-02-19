package com.example.andre.verifypresency.register

import com.example.andre.verifypresency.register.form.User

class RegisterRepository(
        private val usersRemoteDateSource: RegisterRemoteDataSource) {

    fun createUser(user: User, callBack: RegisterDataSource.SaveUserCallback) {

        this.usersRemoteDateSource.createUser(user, object : RegisterDataSource.SaveUserCallback {

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
        private var INSTANCE: RegisterRepository? = null

        fun getInstance(usersRemoteDateSource: RegisterRemoteDataSource): RegisterRepository =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: RegisterRepository(usersRemoteDateSource).also { INSTANCE = it }
                }

    }
}