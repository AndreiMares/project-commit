package com.example.andre.verifypresency.login

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.activities.BaseActivity
import com.example.andre.verifypresency.util.replaceFragmentInActivity

class LoginActivity : BaseActivity() {

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