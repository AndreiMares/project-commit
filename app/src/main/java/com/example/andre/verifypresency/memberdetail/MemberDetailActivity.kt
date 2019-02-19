package com.example.andre.verifypresency.memberdetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.BaseActivity
import com.example.andre.verifypresency.databinding.ActivityMemberDetailBinding
import kotlinx.android.synthetic.main.snippet_top_detailbar.*

class MemberDetailActivity : BaseActivity() {

    private lateinit var memberViewDetailModel: MemberDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMemberDetailBinding>(this, R.layout.activity_member_detail)

        this.configureViews()

        this.memberViewDetailModel = this.obtainViewModel(MemberDetailViewModel::class.java)

        binding.model = this.memberViewDetailModel

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

        this.memberViewDetailModel.getMessage().observe(this, Observer {

            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
        //listen for changes

    }

    private fun <T : ViewModel> obtainViewModel(viewModelClass: Class<T>): T =
            ViewModelProviders.of(this, MemberDetailModelFactory.getInstance()).get(viewModelClass)

    private fun configureViews() {
        //sets the custom toolbar
        setSupportActionBar(snippet_top_detailbar_tb_header)

        //sets the arrow back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        //sets the title, method must be done to check if its a visualize or create page
        snippet_top_detailbar_tv_title.text = resources.getString(R.string.activity_member_detail_create)
    }
}