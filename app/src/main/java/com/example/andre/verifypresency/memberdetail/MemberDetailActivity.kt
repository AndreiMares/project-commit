package com.example.andre.verifypresency.memberdetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.Snackbar
import android.view.MenuItem
import android.widget.Toast
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.BaseActivity
import com.example.andre.verifypresency.databinding.ActivityMemberDetailBinding
import com.example.andre.verifypresency.main.MainActivity
import com.example.andre.verifypresency.main.MemberListFragment.Companion.EDIT
import com.example.andre.verifypresency.main.MemberListFragment.Companion.MEMBER_NAME
import kotlinx.android.synthetic.main.snippet_top_detailbar.*

class MemberDetailActivity : BaseActivity() {

    private lateinit var memberViewDetailModel: MemberDetailViewModel
    private lateinit var viewBinding: ActivityMemberDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.viewBinding = DataBindingUtil.setContentView<ActivityMemberDetailBinding>(this, R.layout.activity_member_detail)

        this.memberViewDetailModel = ViewModelProviders.of(this, MemberDetailModelFactory.getInstance()).get(MemberDetailViewModel::class.java)

        this.viewBinding.model = this.memberViewDetailModel

        this.configureViews(resources.getString(R.string.activity_member_detail_create))

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

    override fun onStart() {
        super.onStart()

        this.memberViewDetailModel.apply {
            navigation.observe(this@MemberDetailActivity, Observer<Unit> {

                finish()

            })
        }

        this.memberViewDetailModel.apply {
            snackbarMessage.observe(this@MemberDetailActivity, Observer<String> { it ->

                it?.let { Snackbar.make(viewBinding.root, it, Snackbar.LENGTH_SHORT).show() }
            })
        }

    }

    override fun onResume() {
        super.onResume()

        intent.extras?.let {

            if (it.getBoolean(EDIT)) {
                //sets the title, method must be done to check if its a visualize or create page
                this.configureViews(resources.getString(R.string.activity_member_detail_update))

                this.viewBinding.model?.loadMember(it.getBoolean(EDIT), it.getString(MEMBER_NAME, ""))
            }
        }
    }

    private fun configureViews(resource: String) {
        //sets the custom toolbar
        setSupportActionBar(snippet_top_detailbar_tb_header)

        //sets the arrow back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        //sets the title, method must be done to check if its a visualize or create page
        snippet_top_detailbar_tv_title.text = resource
    }
}