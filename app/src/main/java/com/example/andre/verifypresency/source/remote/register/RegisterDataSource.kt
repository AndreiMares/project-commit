package com.example.andre.verifypresency.source.remote.register

import com.example.andre.verifypresency.source.models.User

interface RegisterDataSource {

    interface LoadUsersCallback {

        fun onUsersLoaded(users: List<User>)

        fun onDataNotAvailable()
    }

    interface LoadUserCallback {

        fun onUserLoaded(users: User)

        fun onDataNotAvailable()
    }

    interface SaveUserCallback {

        fun onUserSaved(message: String)

        fun onSaveFailed(message: String)
    }

    fun getUsers(callback: LoadUserCallback)

    fun getUser(userId: String, callback: LoadUserCallback)

    fun saveUser(user: User, callback: SaveUserCallback)

}