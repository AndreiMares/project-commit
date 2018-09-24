package com.example.andre.verifypresency.info

import android.os.Bundle
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.BaseActivity

class InfoActivity : BaseActivity() {

    private val INFO_NUM = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        super.configureBottomNav(this.INFO_NUM)
    }

}
