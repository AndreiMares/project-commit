package com.example.andre.verifypresency.register.remote

interface RegisterDataSource {

    interface SaveUserCallback {

        fun onUserSaved(message: String)

        fun onSaveFailed(message: String)
    }


}