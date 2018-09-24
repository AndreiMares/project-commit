package com.example.andre.verifypresency

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.andre.verifypresency.util.enableNavigation
import com.example.andre.verifypresency.util.setupBottomNavigationView
import com.example.andre.verifypresency.util.setupCheckedMenuItem
import kotlinx.android.synthetic.main.layout_bottom_navigation_view.*

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overridePendingTransition(0, 0)


    }

    protected fun configureBottomNav(index: Int){
        bottomNavView_bar.setupBottomNavigationView()
        bottomNavView_bar.enableNavigation(this)
        bottomNavView_bar.setupCheckedMenuItem(index)
    }

}