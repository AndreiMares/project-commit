package com.example.andre.verifypresency.main

import android.os.Bundle
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.BaseActivity
import kotlinx.android.synthetic.main.layout_center_viewpager.*
import kotlinx.android.synthetic.main.layout_top_tabs.*

class MainActivity : BaseActivity() {

    private val MAIN_NUM = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        super.configureBottomNav(this.MAIN_NUM)

        this.setupViewPager()

     /*   //testing
        val db = AppDatabase.getInstance(this)*/

    }

    private fun setupViewPager(){
        val adapter = SectionPagerAdapter(supportFragmentManager)
        container.currentItem
        adapter.addFragment(EventFragment())
        adapter.addFragment(MembersFragment())

        //initialized viewPager with an adapter
        container.adapter = adapter

        //initialized tabLayout with the required viewPager
        layout_top_tl_tabs.setupWithViewPager(container)

        layout_top_tl_tabs.getTabAt(0)?.setIcon(R.drawable.ic_events)
        layout_top_tl_tabs.getTabAt(1)?.setIcon(R.drawable.ic_group_ppl)




    }

    private fun findOrCreateMembersFragment(){

    }

}

