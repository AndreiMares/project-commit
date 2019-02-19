package com.example.andre.verifypresency

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.andre.verifypresency.login.LoginActivity
import com.example.andre.verifypresency.util.enableNavigation
import com.example.andre.verifypresency.util.setupBottomNavigationView
import com.example.andre.verifypresency.util.setupCheckedMenuItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.layout_bottom_navigation_view.*
import kotlinx.android.synthetic.main.layout_progressbar.*

open class BaseActivity : AppCompatActivity() {

    private val TAG = "BaseActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overridePendingTransition(0, 0)


    }

    private fun checkAuthState() {
        Log.d(TAG, "checkAuthState: checking authentication state.")

        val user = FirebaseAuth.getInstance().currentUser

        if (user == null) {

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    protected fun configureBottomNav(index: Int) {
        bottomNavView_bar.setupBottomNavigationView()
        bottomNavView_bar.enableNavigation(this)
        bottomNavView_bar.setupCheckedMenuItem(index)
    }

}