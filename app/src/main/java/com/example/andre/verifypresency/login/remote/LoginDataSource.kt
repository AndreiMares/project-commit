package com.example.andre.verifypresency.login.remote

interface LoginDataSource {

    interface LoginCallback {

        fun onLoginSuccess()

        fun onLoginFailed(message: String)
    }
}