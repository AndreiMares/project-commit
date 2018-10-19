package com.example.andre.verifypresency.register

import android.os.Bundle
import com.example.andre.verifypresency.BaseActivity
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.util.replaceFragmentInActivity
import com.example.andre.verifypresency.util.setupActionBar
import kotlinx.android.synthetic.main.snippet_top_registerbar.*

class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        this.findOrCreateEventsFragment()

        setupActionBar(R.id.snippet_top_registerbar_tb_header)

    }

    private fun findOrCreateEventsFragment() =
            supportFragmentManager.findFragmentById(R.id.activity_register_fl_fragment)
                    ?: RegisterFragment.newInstance().also {
                        replaceFragmentInActivity(it, R.id.activity_register_fl_fragment)
                    }

}