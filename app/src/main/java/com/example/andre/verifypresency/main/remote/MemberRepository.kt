package com.example.andre.verifypresency.main.remote

import com.example.andre.verifypresency.main.form.Member

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

    fun getMembersList(callBack: MemberDataSource.LoadListCallback<Member>) {

        this.mMemberRemoteDataSource.getMemberList(object : MemberDataSource.LoadListCallback<Member> {

            override fun onListLoaded(users: List<Member>) {

                callBack.onListLoaded(users)
            }

            override fun onError() {

                callBack.onError()
            }

        })

    }

    fun getMember(id: String, callBack: MemberDataSource.LoadSingleCallback<Member>) {

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