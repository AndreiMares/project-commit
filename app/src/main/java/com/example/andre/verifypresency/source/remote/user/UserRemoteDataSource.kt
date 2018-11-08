package com.example.andre.verifypresency.source.remote.user

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.andre.verifypresency.source.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class UserRemoteDataSource {

    private val message: MutableLiveData<String>? = null

    fun addUser(user: User) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.email!!, user.password!!).addOnCompleteListener { task ->

            if (task.isSuccessful) {

                this.sendVerificationEmail()

                FirebaseAuth.getInstance().signOut()

            } else {

                if (task.exception is FirebaseAuthException) {
                    val errorCode = (task.exception as FirebaseAuthException).errorCode

                    when (errorCode) {
                        "ERROR_INVALID_EMAIL" -> {
                            message?.value = "The email address is badly formatted."
                        }

                        "ERROR_EMAIL_ALREADY_IN_USE" -> {
                            message?.value = "The email address is already in use by another account."
                        }

                        "ERROR_CREDENTIAL_ALREADY_IN_USE" -> {
                            message?.value = "This credential is already associated with a different user account. "
                        }

                        "ERROR_WEAK_PASSWORD" -> {
                            message?.value = "The password is invalid it must 6 characters at least"
                        }

                        else -> message?.value = "Unable to Register"
                    }
                }
            }
        }
    }

    fun getMessage() = message as LiveData<String>

    private fun sendVerificationEmail() {
        val user = FirebaseAuth.getInstance().currentUser

        user?.sendEmailVerification()?.addOnCompleteListener { task ->

            if (task.isSuccessful)

                message?.value = "An email has been sent for validation"
            else
                message?.value = "Failed to send validation email"

        }
    }
}