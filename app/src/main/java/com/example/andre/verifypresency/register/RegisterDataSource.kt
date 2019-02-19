package com.example.andre.verifypresency.register

interface RegisterDataSource {

    interface SaveUserCallback {

        fun onUserSaved(message: String)

        fun onSaveFailed(message: String)
    }


}