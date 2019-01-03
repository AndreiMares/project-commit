package com.example.andre.verifypresency.event

import android.os.Bundle
import android.view.MenuItem
import com.example.andre.verifypresency.BaseActivity
import com.example.andre.verifypresency.R
import kotlinx.android.synthetic.main.snippet_top_profilebar.*

class EventActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        setSupportActionBar(snippet_top_profilebar_tb_header)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when (item?.itemId) {

            android.R.id.home -> {
                super.onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}