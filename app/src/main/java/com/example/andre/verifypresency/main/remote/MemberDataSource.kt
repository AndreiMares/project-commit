package com.example.andre.verifypresency.main.remote

interface MemberDataSource {

    interface LoadListCallback<T> {

        fun onListLoaded(users: List<T>)

        fun onError()
    }

    interface LoadSingleCallback<T> {

        fun onSuccess(member: T)

        fun onFailed(message: String?)
    }

    interface SaveCallback {

        fun onSuccess(message: String)

        fun onFailed(message: String)
    }

    interface UpdateCallback {

        fun onSuccess()

        fun onFailed(message: String?)
    }

    interface DeleteCallback {

        fun onSuccess()

        fun onFailed(message: String?)
    }
}