package com.example.andre.verifypresency.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.andre.verifypresency.BaseActivity
import com.example.andre.verifypresency.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        activity_register_btn_register.setOnClickListener { this.registerClicked() }


    }

    private fun registerClicked() {

        super.startProgressBar()

        when (this.validateFields(activity_register_et_email.text.toString()
                , activity_register_et_password.text.toString()
                , activity_register_et_confirmation.text.toString())) {

            true -> this.registerNewEmail(activity_register_et_email.text.toString()
                    , activity_register_et_password.text.toString())

            false -> super.hideProgressBar()
        }
    }

    /**
     * Registers a new account into FireBase
     */
    private fun registerNewEmail(email: String, password: String) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

            if (task.isSuccessful) {

                this.sendVerificationEmail()

                FirebaseAuth.getInstance().signOut()

                this.redirectToLoginScreen()

            } else {

                if (task.exception is FirebaseAuthException) {
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

    /**
     * Returns true if both email and password fields are valid, otherwise returns false
     */
    private fun validateFields(email: String, password: String, confirmPassword: String): Boolean {

        if (!email.isEmpty()
                && !password.isEmpty()
                && !confirmPassword.isEmpty()) {

            if (password.equals(confirmPassword, false)) {

                return true

            } else {
                Toast.makeText(this@RegisterActivity, "Passwords do not match", Toast.LENGTH_SHORT).show()
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