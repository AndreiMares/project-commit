package com.example.andre.verifypresency.source.remote.login

interface LoginDataSource {

    interface LoginCallback {

        fun onLoginSuccess()

        fun onLoginFailed(message: String)
    }
}