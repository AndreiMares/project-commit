package com.example.andre.verifypresency.activities

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.fragments.LoginFragment
import com.example.andre.verifypresency.viewmodel.LoginModelFactory
import com.example.andre.verifypresency.viewmodel.LoginViewModel
import com.example.andre.verifypresency.util.replaceFragmentInActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class LoginActivity : BaseActivity() {

    private var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        this.findOrCreateEventsFragment()

    }

    private fun findOrCreateEventsFragment() =
            supportFragmentManager.findFragmentById(R.id.activity_login_fl_fragment)
                    ?: LoginFragment.newInstance().also {
                        replaceFragmentInActivity(it, R.id.activity_login_fl_fragment)
                    }


    fun obtainViewModel(): LoginViewModel = this.obtainViewModel(LoginViewModel::class.java)

    private fun <T : ViewModel> obtainViewModel(viewModelClass: Class<T>): T =
            ViewModelProviders.of(this, LoginModelFactory.getInstance()).get(viewModelClass)

}