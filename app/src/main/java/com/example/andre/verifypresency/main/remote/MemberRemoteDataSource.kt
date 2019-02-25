package com.example.andre.verifypresency.main.remote

import android.util.Log
import com.example.andre.verifypresency.main.form.Member
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MemberRemoteDataSource {

    private var TAG = "MemberRemoteDataSource"

    private val mDB = FirebaseFirestore.getInstance()

    fun saveMember(member: Member, callBack: MemberDataSource.SaveCallback) {

        val memberDetail = HashMap<String, Any>()

        FirebaseAuth.getInstance().currentUser?.let { memberDetail.put("UserId", it.uid) }
        member.name.let { memberDetail.put("Name", it) }
        member.email.let { memberDetail.put("Email", it) }
        member.phoneNumber.let { memberDetail.put("PhoneNumber", it) }
        member.active.let { memberDetail.put("Active", true) }

        //we need to verify if we already have aa member register on current logged user
        val key = member.name.replace("\\s".toRegex(), "")

        this.mDB.collection("Member")
                .whereEqualTo("UserId", memberDetail.getValue("UserId"))
                .whereEqualTo("Name", key)
                .get()
                .addOnCompleteListener { task ->
                    when {
                        task.isSuccessful ->
                            if (task.result?.documents?.size!! > 0)
                                callBack.onFailed("There is already a user registered with this email address.")
                            else
                                this.saveMember(memberDetail, callBack)
                    }
                }
                .addOnFailureListener { exception ->
                    exception.message?.let { callBack.onFailed(it) }
                }
    }

    fun getMemberList(callBack: MemberDataSource.LoadListCallback<Member>) {

        val key = FirebaseAuth.getInstance().currentUser?.uid

        this.mDB.collection("Member")
                .whereEqualTo("UserId", key)
                .get()
                .addOnSuccessListener { documents ->

                    val queryList = arrayListOf<Member>()

                    documents.forEach {
                        val member = it.toObject(Member::class.java)
                        member.memberId = it.id
                        queryList.add(member)

                    }

                    callBack.onListLoaded(queryList)
                }
                .addOnFailureListener { exception ->

                    exception.message?.let { callBack.onError() }
                    Log.w(TAG, "Error getting documents: ", exception)
                }
    }

    fun deleteMember(member: Member, callBack: MemberDataSource.DeleteCallback) {

        this.mDB.collection("Member").document(member.memberId)
                .delete()
                .addOnSuccessListener { callBack.onSuccess() }
                .addOnFailureListener { exception -> callBack.onFailed(exception.message) }

    }

    fun getMember(name: String, callBack: MemberDataSource.LoadSingleCallback<Member>) {

        val userId = FirebaseAuth.getInstance().currentUser?.uid

        this.mDB.collection("Member")
                .whereEqualTo("UserId", userId)
                .whereEqualTo("Name", name)
                .get()
                .addOnSuccessListener { documents ->

                    val member = documents.toObjects(Member::class.java)[0]
                    member.memberId = documents.documentChanges[0].document.id

                    callBack.onSuccess(member)

                }
                .addOnFailureListener { exception ->

                    exception.message?.let { callBack.onFailed(it) }
                }

    }

    fun updateMember(member: Member, callBack: MemberDataSource.UpdateCallback) {

        val memberDetail = HashMap<String, Any>()
        member.email.let { memberDetail.put("Email", it) }
        member.phoneNumber.let { memberDetail.put("PhoneNumber", it) }

        this.mDB.collection("Member")
                .document(member.memberId)
                .update(memberDetail)
                .addOnSuccessListener { callBack.onSuccess() }
                .addOnFailureListener { exception -> callBack.onFailed(exception.message) }
    }

    private fun saveMember(memberDetail: HashMap<String, Any>, callBack: MemberDataSource.SaveCallback) =
            this.mDB.collection("Member").document().set(memberDetail)
                    .addOnCompleteListener { task ->
                        if (task.isComplete && task.isSuccessful) {
                            callBack.onSuccess("Member successfully added!")

                        } else {
                            task.exception?.message?.let { callBack.onFailed(it) }

                        }
                    }

}