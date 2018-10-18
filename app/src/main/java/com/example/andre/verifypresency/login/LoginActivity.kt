package com.example.andre.verifypresency.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.andre.verifypresency.BaseActivity
import com.example.andre.verifypresency.R
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setupFireBaseAuth()

        setContentView(R.layout.activity_login)

        activity_login_tv_register.setOnClickListener { this.navigateRegisterActivity() }

        activity_login_tv_forgotPass.setOnClickListener { }

        activity_login_tv_resendEmail.setOnClickListener {
            this.resendVerificationEmail(activity_login_et_email.text.toString()
                    , activity_login_et_password.text.toString())
        }

        activity_login_btn_sign.setOnClickListener {
            this.login(activity_login_et_email.text.toString()
                    , activity_login_et_password.text.toString())
        }

    }

    override fun onStart() {
        super.onStart()
        FirebaseAuth.getInstance().addAuthStateListener(this.mAuthListener!!)
    }

    override fun onStop() {
        super.onStop()

        if (this.mAuthListener != null)
            FirebaseAuth.getInstance().removeAuthStateListener(this.mAuthListener!!)
    }

    private fun login(email: String, password: String) {

        if (!email.isEmpty()
                && !password.isEmpty()) {

            FirebaseAuth.getInstance().signInWithEmailAndPassword(activity_login_et_email.text.toString(), activity_login_et_password.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {

                            Toast.makeText(this@LoginActivity, "Sign In", Toast.LENGTH_SHORT).show()
                        } else {

                        }


                        hideProgressBar()
                    }

        } else
            Toast.makeText(this@LoginActivity, "You must fill out all the fields", Toast.LENGTH_SHORT).show()

    }

    private fun setupFireBaseAuth() {
        this.mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->

            val user = firebaseAuth.currentUser

            if (user != null) {

                if (user.isEmailVerified) {

                } else {
                    Toast.makeText(this@LoginActivity, "Check your email inbox for a verification link ", Toast.LENGTH_SHORT).show()
                    FirebaseAuth.getInstance().signOut()
                }
            } else {

            }
        }
    }

    /**
     * Used for resending a verification email based on existing credentials
     */
    private fun resendVerificationEmail(email: String, password: String) {

        val authCredential = EmailAuthProvider.getCredential(email, password)

        FirebaseAuth.getInstance().signInWithCredential(authCredential)
                .addOnCompleteListener {

                    if (it.isSuccessful) {
                        super.sendVerificationEmail()
                        FirebaseAuth.getInstance().signOut()
                    }


                }.addOnFailureListener { Toast.makeText(this@LoginActivity, "Invalid credentials\n Reset your password and try again", Toast.LENGTH_SHORT).show() }

        FirebaseAuth.getInstance().signInWithCredential(authCredential).addOnCompleteListener { task ->

        }
    }

    /**
     * Navigating to RegisterActivity
     */
    private fun navigateRegisterActivity() {

        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)

    }

}