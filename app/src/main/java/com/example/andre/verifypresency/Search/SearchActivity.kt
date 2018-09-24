package com.example.andre.verifypresency.Search

import android.os.Bundle
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.BaseActivity

class SearchActivity : BaseActivity() {

    private val SEARCH_NUM = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        super.configureBottomNav(this.SEARCH_NUM)
    }

}
