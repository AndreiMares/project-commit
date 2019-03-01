package com.example.andre.verifypresency.util

import android.content.Context
import android.content.Intent
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.info.InfoActivity
import com.example.andre.verifypresency.main.MainActivity
import com.example.andre.verifypresency.profile.ProfileActivity
import com.example.andre.verifypresency.search.SearchActivity
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx

fun BottomNavigationViewEx.setupBottomNavigationView() {

    this.enableAnimation(false)
    this.enableItemShiftingMode(false)
    this.enableShiftingMode(false)
    this.setTextVisibility(true)

}

fun BottomNavigationViewEx.enableNavigation(context: Context) {

    this.setOnNavigationItemSelectedListener {

        when (it.itemId) {

            R.id.ic_house -> {
                val intent = Intent(context, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                context.startActivity(intent)
                false
            }


            R.id.ic_search -> {
                val intent = Intent(context, SearchActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                context.startActivity(intent)
                false
            }

            R.id.ic_person -> {
                val intent = Intent(context, ProfileActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                context.startActivity(intent)
                false
            }

            R.id.ic_info -> {
                val intent = Intent(context, InfoActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                context.startActivity(intent)
                false
            }

            else -> {
                false
            }
        }

    }

}

fun BottomNavigationViewEx.setupCheckedMenuItem(index: Int) {

    this.menu.getItem(index).isChecked = true

}

//fun String.isEmpty(value: String): Boolean = value.


