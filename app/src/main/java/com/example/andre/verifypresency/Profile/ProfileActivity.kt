package com.example.andre.verifypresency.Profile

import android.os.Bundle
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.BaseActivity

class ProfileActivity : BaseActivity() {

    private val PROFILE_NUM = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

//        super.configureBottomNav(this.PROFILE_NUM)

    }

}
