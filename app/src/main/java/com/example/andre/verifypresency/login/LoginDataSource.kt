package com.example.andre.verifypresency.login

interface LoginDataSource {

    interface LoginCallback {

        fun onLoginSuccess()

        fun onLoginFailed(message: String)
    }
}