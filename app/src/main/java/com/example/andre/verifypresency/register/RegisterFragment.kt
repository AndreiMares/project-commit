package com.example.andre.verifypresency.register

import android.content.Intent
import android.os.BaseBundle
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.andre.verifypresency.BaseFragment
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment: BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_register, container, false)

        return view


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragment_register_btn_register.setOnClickListener { this.registerClicked() }
    }

    private fun registerClicked() {

        super.startProgressBar()

        when (this.validateFields(fragment_register_et_email.text.toString()
                , fragment_register_et_password.text.toString()
                , fragment_register_et_confirmation.text.toString())) {

            true -> this.registerNewEmail(fragment_register_et_email.text.toString()
                    , fragment_register_et_password.text.toString())

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
                            fragment_register_et_email.error = "The email address is badly formatted."
                            fragment_register_et_email.requestFocus()
                        }

                        "ERROR_EMAIL_ALREADY_IN_USE" -> {
                            fragment_register_et_email.error = "The email address is already in use by another account."
                            fragment_register_et_email.requestFocus()
                        }

                        "ERROR_CREDENTIAL_ALREADY_IN_USE" -> {
                            fragment_register_et_email.error = "This credential is already associated with a different user account. "
                            fragment_register_et_email.requestFocus()
                        }

                        "ERROR_WEAK_PASSWORD" -> {
                            fragment_register_et_password.error = "The password is invalid it must 6 characters at least"
                            fragment_register_et_password.requestFocus()
                        }

                        else -> Toast.makeText(context, "Unable to Register", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return false
            }

        } else {
            Toast.makeText(context, "You must fill out all the fields", Toast.LENGTH_SHORT).show()
            return false
        }

    }

    /**
     * Redirects user to Login Screen
     */
    private fun redirectToLoginScreen() {

        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()

    }


    companion object {
        fun newInstance() = RegisterFragment()
    }
}