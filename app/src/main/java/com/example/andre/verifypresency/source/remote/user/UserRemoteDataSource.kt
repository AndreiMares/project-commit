package com.example.andre.verifypresency.source.remote.user

import com.example.andre.verifypresency.source.models.User
import com.google.firebase.auth.FirebaseAuth

class UserRemoteDataSource {

    fun saveUser(user: User, callBack: UserDataSource.SaveUserCallback) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.email!!, user.password!!).addOnCompleteListener { task ->

            if (task.isSuccessful) {

                this.sendVerificationEmail(callBack)

                FirebaseAuth.getInstance().signOut()

            } else {

                task.exception?.message?.let { callBack.onSaveFailed(it) }

            }
        }
    }

    private fun sendVerificationEmail(callBack: UserDataSource.SaveUserCallback) {
        val user = FirebaseAuth.getInstance().currentUser

        user?.sendEmailVerification()?.addOnCompleteListener { task ->

            if (task.isSuccessful)

                callBack.onUserSaved("An email has been sent for validation")
            else
                callBack.onSaveFailed("Failed to send validation email")

        }
    }

}