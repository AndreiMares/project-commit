package com.example.andre.verifypresency

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.andre.verifypresency.util.enableNavigation
import com.example.andre.verifypresency.util.setupBottomNavigationView
import com.example.andre.verifypresency.util.setupCheckedMenuItem
import kotlinx.android.synthetic.main.layout_bottom_navigation_view.*
import kotlinx.android.synthetic.main.layout_progressbar.*

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overridePendingTransition(0, 0)


    }

    protected fun configureBottomNav(index: Int) {
        bottomNavView_bar.setupBottomNavigationView()
        bottomNavView_bar.enableNavigation(this)
        bottomNavView_bar.setupCheckedMenuItem(index)
    }

    protected fun startProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }


    protected fun hideProgressBar() {
        if(progressBar?.visibility == View.VISIBLE)
            progressBar.visibility = View.INVISIBLE
    }

}