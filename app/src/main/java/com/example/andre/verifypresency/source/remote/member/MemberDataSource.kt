package com.example.andre.verifypresency.source.remote.member

import com.example.andre.verifypresency.source.models.Member

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
}