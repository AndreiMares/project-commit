package com.example.andre.verifypresency.source.remote.member

import android.arch.lifecycle.MutableLiveData
import com.example.andre.verifypresency.source.models.Member

interface MemberDataSource {

    interface LoadListCallback {

        fun onListLoaded(users: List<Member>)

        fun onDataNotAvailable()

        fun onError(message: String)
    }

    interface LoadSingleCallback {

        fun onSingleLoaded(member: Member)

        fun onDataNotAvailable()
    }

    interface SaveCallback {

        fun onSaveSuccess(message: String)

        fun onSaveFailed(message: String)
    }
}