package com.example.andre.verifypresency.source.remote.member

import android.util.Log
import com.example.andre.verifypresency.source.models.Member
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

        //we need to verify if we already have an user
        val key = member.name.replace("\\s".toRegex(), "")

        val docRef = this.mDB.collection("Member").document(key)
        docRef.get()
                .addOnSuccessListener { document ->

                    if (document.data != null) {

                        callBack.onSaveFailed("There is already a user registered with this email address.")
                        Log.d(TAG, "DocumentSnapshot data: " + document.data)

                    } else {
                        //if there is no user with the inserted email address, insert a new user into firestore
                        this.saveMember(key, memberDetail, callBack)
                    }
                }
                .addOnFailureListener { exception ->
                    exception.message?.let { callBack.onSaveFailed(it) }

                    Log.d(TAG, "get failed with ", exception)
                }

    }

    private fun saveMember(key: String, memberDetail: HashMap<String, Any>, callBack: MemberDataSource.SaveCallback) {

        this.mDB.collection("Member").document(key).set(memberDetail)
                .addOnCompleteListener { task ->
                    if (task.isComplete && task.isSuccessful) {
                        callBack.onSaveSuccess("Member successfully added!")

                    } else {
                        task.exception?.message?.let { callBack.onSaveFailed(it) }

                    }
                }
    }
}