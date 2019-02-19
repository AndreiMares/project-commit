package com.example.andre.verifypresency.register

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.activities.BaseActivity
import com.example.andre.verifypresency.login.LoginActivity
import com.example.andre.verifypresency.util.replaceFragmentInActivity
import com.example.andre.verifypresency.util.setupActionBar

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

        startActivity(intent)
        finish()

    }

    /**
     * Obtaining specific ViewModel
     */
    fun obtainViewModel(): RegisterViewModel = this.obtainViewModel(RegisterViewModel::class.java)

    //endregion

    //region Private Functions

    private fun findOrCreateEventsFragment() =
            supportFragmentManager.findFragmentById(R.id.activity_register_fl_fragment)
                    ?: RegisterFragment.newInstance().also {
                        replaceFragmentInActivity(it, R.id.activity_register_fl_fragment)
                    }

    private fun <T : ViewModel> obtainViewModel(viewModelClass: Class<T>): T =
            ViewModelProviders.of(this, RegisterModelFactory.getInstance()).get(viewModelClass)

    //endregion

}