package com.example.andre.verifypresency.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.andre.verifypresency.BaseActivity
import com.example.andre.verifypresency.R
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

        activity_login_btn_sign.setOnClickListener { this.login() }

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

    private fun navigateRegisterActivity() {

        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)

    }

    private fun setupFireBaseAuth() {
        this.mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->

            val user = firebaseAuth.currentUser

            if (user != null)
                Toast.makeText(this@LoginActivity, "Signed in", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this@LoginActivity, "Sign out", Toast.LENGTH_SHORT).show()


        }
    }

    private fun login() {

        if (!activity_login_et_email.text.isEmpty()
                && !activity_login_et_password.text.isEmpty()) {


            FirebaseAuth.getInstance().signInWithEmailAndPassword(activity_login_et_email.text.toString(), activity_login_et_password.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {

                        } else {

                        }


                        hideProgressBar()
                    }

        }


    }

}