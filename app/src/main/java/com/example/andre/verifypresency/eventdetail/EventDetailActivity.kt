package com.example.andre.verifypresency.eventdetail

import android.os.Bundle
import android.view.MenuItem
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.BaseActivity
import kotlinx.android.synthetic.main.snippet_top_detailbar.*

class EventDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        //sets the custom toolbar
        setSupportActionBar(snippet_top_detailbar_tb_header)

        //sets the arrow back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        //sets the title, method must be done to check if its a visualize or create page
        snippet_top_detailbar_tv_title.text = resources.getString(R.string.activity_event_detail_create)

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