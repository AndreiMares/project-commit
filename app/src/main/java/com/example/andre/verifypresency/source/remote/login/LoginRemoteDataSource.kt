package com.example.andre.verifypresency.source.remote.login

import com.example.andre.verifypresency.form.login.LoginFields
import com.google.firebase.auth.FirebaseAuth

class LoginRemoteDataSource {

    fun login(data: LoginFields, callBack: LoginDataSource.LoginCallback) {

        FirebaseAuth.getInstance().signInWithEmailAndPassword(data.email!!, data.password!!)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        val user = FirebaseAuth.getInstance().currentUser

                        if (user != null) {

                            if (user.isEmailVerified) {

                                callBack.onLoginSuccess()

                            } else {
                                callBack.onLoginFailed("Check your email inbox for a verification link")
                                FirebaseAuth.getInstance().signOut()
                            }
                        }

                    } else {
                        task.exception?.message?.let { callBack.onLoginFailed(it) }
                    }
                }
    }
}