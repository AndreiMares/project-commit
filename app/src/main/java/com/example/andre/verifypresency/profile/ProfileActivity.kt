package com.example.andre.verifypresency.profile

import android.os.Bundle
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.BaseActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.snippet_top_profilebar.*

class ProfileActivity : BaseActivity() {

    private val PROFILE_NUM = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        super.configureBottomNav(this.PROFILE_NUM)

        this.getUserDetails()

    }

    private fun getUserDetails(){
        val user = FirebaseAuth.getInstance().currentUser

        snippet_top_profileBar_tv_profileName.text = user?.email

    }

}
