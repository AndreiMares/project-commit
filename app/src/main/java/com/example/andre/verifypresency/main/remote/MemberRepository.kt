package com.example.andre.verifypresency.main.remote

import com.example.andre.verifypresency.main.form.Member

class MemberRepository(
        private val mMemberRemoteDataSource: MemberRemoteDataSource) {

    fun saveMember(member: Member, callBack: MemberDataSource.SaveCallback) =
            this.mMemberRemoteDataSource.saveMember(member, object : MemberDataSource.SaveCallback {

                override fun onSuccess(message: String) {

                    callBack.onSuccess(message)
                }

                override fun onFailed(message: String) {

                    callBack.onFailed(message)
                }

            })

    fun getMembersList(callBack: MemberDataSource.LoadListCallback<Member>) =
            this.mMemberRemoteDataSource.getMemberList(object : MemberDataSource.LoadListCallback<Member> {

                override fun onListLoaded(users: List<Member>) = callBack.onListLoaded(users)

                override fun onError() = callBack.onError()
            })

    fun getMember(name: String, callBack: MemberDataSource.LoadSingleCallback<Member>) =
            this.mMemberRemoteDataSource.getMember(name, object : MemberDataSource.LoadSingleCallback<Member> {

                override fun onSuccess(member: Member) = callBack.onSuccess(member)

                override fun onFailed(message: String?) = callBack.onFailed(message)
            })

    fun updateMember(member: Member, callBack: MemberDataSource.UpdateCallback) =
            this.mMemberRemoteDataSource.updateMember(member, object : MemberDataSource.UpdateCallback {

                override fun onSuccess() {

                    callBack.onSuccess()
                }

                override fun onFailed(message: String?) {

                    callBack.onFailed(message)
                }

            })

    fun deleteMember(member: Member, callBack: MemberDataSource.DeleteCallback) =
            this.mMemberRemoteDataSource.deleteMember(member, object : MemberDataSource.DeleteCallback {

                override fun onSuccess() = callBack.onSuccess()

                override fun onFailed(message: String?) = callBack.onFailed(message)
            })

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