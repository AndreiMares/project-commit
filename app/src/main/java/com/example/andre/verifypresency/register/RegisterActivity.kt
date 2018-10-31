package com.example.andre.verifypresency.register

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.example.andre.verifypresency.BaseActivity
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.login.LoginActivity
import com.example.andre.verifypresency.register.model.RegisterViewModel
import com.example.andre.verifypresency.util.obtainViewModel
import com.example.andre.verifypresency.util.replaceFragmentInActivity
import com.example.andre.verifypresency.util.setupActionBar
import kotlinx.android.synthetic.main.snippet_top_registerbar.*

class RegisterActivity : BaseActivity() {

    //region Override

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        this.findOrCreateEventsFragment()

        setupActionBar(R.id.snippet_top_registerbar_tb_header)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when (item?.itemId) {

            android.R.id.home -> {
                this.redirectToLoginScreen()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    //endregion

    //region Public Functions

    /**
     * Redirects user to Login Screen
     */
    fun redirectToLoginScreen() {

        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()

    }

    /**
     * Obtaining specific ViewModel
     */
    fun obtainViewModel(): RegisterViewModel = obtainViewModel(RegisterViewModel::class.java)

    //endregion

    //region Private Functions

    private fun findOrCreateEventsFragment() =
            supportFragmentManager.findFragmentById(R.id.activity_register_fl_fragment)
                    ?: RegisterFragment.newInstance().also {
                        replaceFragmentInActivity(it, R.id.activity_register_fl_fragment)
                    }

    //endregion

}