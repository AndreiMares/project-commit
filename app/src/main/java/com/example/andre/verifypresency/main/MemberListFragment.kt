package com.example.andre.verifypresency.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.memberdetail.MemberDetailActivity
import com.example.andre.verifypresency.databinding.FragmentMemberListBinding
import com.example.andre.verifypresency.memberdetail.MemberDetailModelFactory

class MemberListFragment : Fragment() {

    companion object {
        val EDIT = "EDIT"
        val MEMBER_NAME = "MEMBER_NAME"
    }


    private lateinit var viewBinding: FragmentMemberListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_list, container, false)

        this.viewBinding.model = ViewModelProviders.of(this, MemberDetailModelFactory.getInstance()).get(MemberViewModel::class.java)

        this.viewBinding.fragmentMemberFab.setOnClickListener { this.openMemberActivity(false, "") }

        //bottom sheet observer
        val bottomSheetBehavior = BottomSheetBehavior.from(this.viewBinding.snippetSearchBar)
        this.viewBinding.model?.apply {
            onBottomSheetBehaviorState.observe(this@MemberListFragment, Observer<Unit> {

                if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                } else {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            })
        }

        //navigate to detail observer
        this.viewBinding.model?.apply {
            onNavigation.observe(this@MemberListFragment, Observer<String> { name ->

                name?.let { openMemberActivity(true, it) }
            })
        }

        return this.viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.setAdapter()
    }

    override fun onResume() {
        super.onResume()

        this.viewBinding.model?.prepareLoadingList()
    }

    private fun openMemberActivity(value: Boolean, name: String) {
        val intent = Intent(context, MemberDetailActivity::class.java)
        intent.putExtra(EDIT, value)
        intent.putExtra(MEMBER_NAME, name)
        startActivity(intent)
    }

    private fun setAdapter() {
        val viewModel = this.viewBinding.model

        if (viewModel != null) {
            val listAdapter = MemberListAdapter(ArrayList(0), viewModel)
            val viewManager = LinearLayoutManager(context)

            this.viewBinding.fragmentRecycleView.adapter = listAdapter
            this.viewBinding.fragmentRecycleView.layoutManager = viewManager

        }
    }

}