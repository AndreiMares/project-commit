package com.example.andre.verifypresency.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.andre.verifypresency.BaseActivity
import com.example.andre.verifypresency.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
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
//            false -> Toast.makeText(this@RegisterActivity, "Unable to Register", Toast.LENGTH_SHORT).show()
        }


    }

    private fun registerNewEmail(email: String, password: String) {

        super.startProgressBar()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { v ->

            if (v.isComplete) {

                val id = FirebaseAuth.getInstance().currentUser?.uid

                FirebaseAuth.getInstance().signOut()
            } else {
                Toast.makeText(this@RegisterActivity, "Unable to Register", Toast.LENGTH_SHORT).show()
            }

            if(v.isCanceled){

                val
            }

            super.hideProgressBar()
        }

    }

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
}