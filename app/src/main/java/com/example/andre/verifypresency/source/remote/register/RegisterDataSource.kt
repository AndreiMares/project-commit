package com.example.andre.verifypresency.source.remote.register

interface RegisterDataSource {

    interface SaveUserCallback {

        fun onUserSaved(message: String)

        fun onSaveFailed(message: String)
    }


}