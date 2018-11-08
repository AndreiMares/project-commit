package com.example.andre.verifypresency.source.remote.user

import com.example.andre.verifypresency.source.models.User

interface UserDataSource {

    interface LoadUsersCallback {

        fun onUsersLoaded(users: List<User>)

        fun onDataNotAvailable()
    }

    interface LoadUserCallback {

        fun onUserLoaded(users: User)

        fun onDataNotAvailable()
    }

    interface SaveUserCallback {

        fun onUserSaved(users: User)

        fun onSaveFailed()
    }

    fun getUsers(callback: LoadUserCallback)

    fun getUser(userId: String, callback: LoadUserCallback)

    fun saveUser(user: User, callback: SaveUserCallback)

}