package com.example.andre.verifypresency.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.andre.verifypresency.BaseActivity
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.main.MainActivity
import com.example.andre.verifypresency.register.RegisterActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setupFireBaseAuth()

        setContentView(R.layout.activity_login)

        this.setUpListeners()

    }

    override fun onStart() {
        super.onStart()
//        FirebaseAuth.getInstance().addAuthStateListener(this.mAuthListener!!)
    }

    override fun onStop() {
        super.onStop()

//        if (this.mAuthListener != null)
//            FirebaseAuth.getInstance().removeAuthStateListener(this.mAuthListener!!)
    }

    //region Private Functions

    private fun setUpListeners(){

        activity_login_iv_image.setOnClickListener { this.fillWithEmailAndPassword() }

        activity_login_tv_register.setOnClickListener { this.navigateRegisterActivity() }

        activity_login_tv_forgotPass.setOnClickListener { }

        activity_login_tv_resendEmail.setOnClickListener {
            this.resendVerificationEmail(activity_login_et_email.text.toString()
                    , activity_login_et_password.text.toString())
        }

        activity_login_btn_sign.setOnClickListener {
            super.startProgressBar()
            this.login(activity_login_et_email.text.toString()
                    , activity_login_et_password.text.toString())
        }
    }

    private fun login(email: String, password: String) {

        if (!email.isEmpty()
                && !password.isEmpty()) {

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {

                            val user = FirebaseAuth.getInstance().currentUser

                            if (user != null) {

                                if (user.isEmailVerified) {

                                    this.navigateToMainActivity()

                                } else {
                                    Toast.makeText(this@LoginActivity, "Check your email inbox for a verification link ", Toast.LENGTH_SHORT).show()
                                    FirebaseAuth.getInstance().signOut()
                                }
                            }


                        } else {
                            if (it.exception is FirebaseAuthException) {

                                this.setupViewValidation(it.exception as FirebaseAuthException)
                            }
                        }

                        hideProgressBar()
                    }

        } else
            Toast.makeText(this@LoginActivity, "You must fill out all the fields", Toast.LENGTH_SHORT).show()

    }

    /**
     * Not used anymore
     */
    private fun setupFireBaseAuth() {
        this.mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->

            val user = firebaseAuth.currentUser

            if (user != null) {

                if (user.isEmailVerified) {

                    this.navigateToMainActivity()

                } else {
                    Toast.makeText(this@LoginActivity, "Check your email inbox for a verification link ", Toast.LENGTH_SHORT).show()
                    FirebaseAuth.getInstance().signOut()
                }
            }
        }
    }

    /**
     * Sets error values to email and password view
     */
    private fun setupViewValidation(exception: FirebaseAuthException){

        val errorCode = exception.errorCode

        when (errorCode) {
            "ERROR_INVALID_EMAIL" -> {
                activity_login_et_email.error = "The email address is badly formatted."
                activity_login_et_email.requestFocus()
            }

            "ERROR_WRONG_PASSWORD" -> {
                activity_login_et_password.error = "The password is invalid or the user does not have a password."
                activity_login_et_password.requestFocus()
            }

            "ERROR_USER_NOT_FOUND" -> {
                activity_login_et_email.error = "There is no user record corresponding to this identifier. The user may have been deleted."
                activity_login_et_email.requestFocus()
            }

            else -> Toast.makeText(this@LoginActivity, "Unable to Register", Toast.LENGTH_SHORT).show()
        }

    }

    /**
     * Used for resending a verification email based on existing credentials
     */
    private fun resendVerificationEmail(email: String, password: String) {

        if (!email.isEmpty()
                && !password.isEmpty()) {

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

        }else
            Toast.makeText(this@LoginActivity, "You must fill out all the fields", Toast.LENGTH_SHORT).show()
    }

    /**
     * Navigate to RegisterActivity
     */
    private fun navigateRegisterActivity() {

        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)

    }

    /**
     * Navigate to MainActivity
     */
    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun fillWithEmailAndPassword(){
        activity_login_et_email.setText("andrei.mares06@gmail.com")
        activity_login_et_password.setText("21andreimares")

    }

    //endregion

}