package com.example.andre.verifypresency.source.remote.user

import com.example.andre.verifypresency.source.models.User

class UserRepository(
        val usersRemoteDateSource: UserDataSource) : UserDataSource {

    override fun getUsers(callback: UserDataSource.LoadUserCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUser(userId: String, callback: UserDataSource.LoadUserCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveUser(user: User, callback: UserDataSource.SaveUserCallback) {

        usersRemoteDateSource.saveUser(user, object : UserDataSource.SaveUserCallback {
            override fun onUserSaved(users: User) {

            }

            override fun onSaveFailed() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }


}