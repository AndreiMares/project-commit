package com.example.andre.verifypresency.source.remote.member

import com.example.andre.verifypresency.source.models.Member

class MemberRepository(
        private val mMemberRemoteDataSource: MemberRemoteDataSource) {


    fun saveMember(member: Member, callBack: MemberDataSource.SaveCallback) {

        this.mMemberRemoteDataSource.saveMember(member, object : MemberDataSource.SaveCallback {

            override fun onSaveSuccess(message: String) {

                callBack.onSaveSuccess(message)
            }

            override fun onSaveFailed(message: String) {

                callBack.onSaveFailed(message)
            }

        })
    }

    fun getMembers(id: String, callBack: MemberDataSource.LoadListCallback) {

    }

    fun getMember(id: String, callBack: MemberDataSource.LoadSingleCallback) {

    }


    companion object {
        @Volatile
        private var INSTANCE: MemberRepository? = null

        fun getInstance(memberDetailRemoteDataSource: MemberRemoteDataSource): MemberRepository =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: MemberRepository(memberDetailRemoteDataSource).also { INSTANCE = it }
                }

    }
}