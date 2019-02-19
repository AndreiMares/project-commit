package com.example.andre.verifypresency.login

import com.example.andre.verifypresency.login.form.LoginFields

class LoginRepository(
        private val mLoginRemoteDateSource: LoginRemoteDataSource) {

    fun login(data: LoginFields, callBack: LoginDataSource.LoginCallback) {

        this.mLoginRemoteDateSource.login(data, object : LoginDataSource.LoginCallback {
            override fun onLoginSuccess() {
                callBack.onLoginSuccess()
            }

            override fun onLoginFailed(message: String) {
                callBack.onLoginFailed(message)
            }

        })
    }


    companion object {
        @Volatile
        private var INSTANCE: LoginRepository? = null

        fun getInstance(loginRemoteDateSource: LoginRemoteDataSource): LoginRepository =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: LoginRepository(loginRemoteDateSource).also { INSTANCE = it }
                }

    }
}