package com.example.andre.verifypresency.login

import android.content.Intent
import android.os.Bundle
import com.example.andre.verifypresency.BaseActivity
import com.example.andre.verifypresency.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        activity_login_tv_register.setOnClickListener { navigateRegisterActivity() }

        activity_login_tv_forgotPass.setOnClickListener {  }

    }

    private fun navigateRegisterActivity() {

        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)

    }

}