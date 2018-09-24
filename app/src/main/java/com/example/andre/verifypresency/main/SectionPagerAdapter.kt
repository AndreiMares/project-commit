package com.example.andre.verifypresency.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Class that stores fragments for tabs
 */
class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {


    private val mFragmentList = mutableListOf<Fragment>()


    override fun getItem(position: Int): Fragment {

        return this.mFragmentList[position]
    }

    override fun getCount(): Int {


        return this.mFragmentList.size

    }

    fun addFragment(fragment: Fragment){
        this.mFragmentList.add(fragment)
    }
}