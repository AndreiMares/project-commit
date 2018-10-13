package com.example.andre.verifypresency.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.andre.verifypresency.BaseActivity
import com.example.andre.verifypresency.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        activity_register_btn_register.setOnClickListener { this.registerClicked() }


    }

    private fun registerClicked() {

        when (this.validateFields()) {
            true -> this.registerNewEmail(activity_register_et_email.text.toString()
                    , activity_register_et_password.text.toString())
        }

    }

    /**
     * Registers a new account into FireBase
     */
    private fun registerNewEmail(email: String, password: String) {

        super.startProgressBar()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

            if (task.isSuccessful) {

                this.sendVerificationEmail()

                FirebaseAuth.getInstance().signOut()

                Toast.makeText(this@RegisterActivity, "User successfully created", Toast.LENGTH_LONG).show()

                this.redirectToLoginScreen()

            } else {

                if (task.exception is FirebaseAuthException){
                    val errorCode = (task.exception as FirebaseAuthException).errorCode

                    when (errorCode) {
                        "ERROR_INVALID_EMAIL" -> {
                            activity_register_et_email.error = "The email address is badly formatted."
                            activity_register_et_email.requestFocus()
                        }

                        "ERROR_EMAIL_ALREADY_IN_USE" -> {
                            activity_register_et_email.error = "The email address is already in use by another account."
                            activity_register_et_email.requestFocus()
                        }

                        "ERROR_CREDENTIAL_ALREADY_IN_USE" -> {
                            activity_register_et_email.error = "This credential is already associated with a different user account. "
                            activity_register_et_email.requestFocus()
                        }

                        "ERROR_WEAK_PASSWORD" -> {
                            activity_register_et_password.error = "The password is invalid it must 6 characters at least"
                            activity_register_et_password.requestFocus()
                        }

                        else -> Toast.makeText(this@RegisterActivity, "Unable to Register", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            super.hideProgressBar()
        }
    }

    private fun sendVerificationEmail(){
        val user = FirebaseAuth.getInstance().currentUser;

        user?.sendEmailVerification()?.addOnCompleteListener{task ->




        }

    }

    /**
     * Returns true if both email and password fields are valid, otherwise returns false
     */
    private fun validateFields(): Boolean {

        if (!activity_register_et_email.text.isEmpty()
                && !activity_register_et_password.text.isEmpty()
                && !activity_register_et_confirmation.text.isEmpty()) {

            if (activity_register_et_password.text.toString().equals(activity_register_et_confirmation.text.toString(), false)) {

                return true

            } else {
                Toast.makeText(this@RegisterActivity, "Passwords do not Match", Toast.LENGTH_SHORT).show()
                return false
            }

        } else {
            Toast.makeText(this@RegisterActivity, "You must fill out all the fields", Toast.LENGTH_SHORT).show()
            return false
        }

    }

    /**
     * Redirects user to Login Screen
     */
    private fun redirectToLoginScreen() {

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()

    }
}