package com.example.andre.verifypresency.main.remote

interface MemberDataSource {

    interface LoadListCallback<T> {

        fun onListLoaded(users: List<T>)

        fun onError()
    }

    interface LoadSingleCallback<T> {

        fun onSingleLoaded(member: T)

        fun onDataNotAvailable()
    }

    interface SaveCallback {

        fun onSaveSuccess(message: String)

        fun onSaveFailed(message: String)
    }

    interface DeleteCallback {

        fun onSuccess()

        fun onFailed(message: String?)
    }
}