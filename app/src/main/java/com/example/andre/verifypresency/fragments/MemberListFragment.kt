package com.example.andre.verifypresency.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.activities.MemberDetailActivity
import com.example.andre.verifypresency.adapters.MemberListAdapter
import com.example.andre.verifypresency.databinding.FragmentMemberListBinding
import com.example.andre.verifypresency.viewmodel.MemberDetailModelFactory
import com.example.andre.verifypresency.viewmodel.MemberViewModel

class MemberListFragment : Fragment() {

    private lateinit var viewBinding: FragmentMemberListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_list, container, false)

        this.viewBinding.model = ViewModelProviders.of(this, MemberDetailModelFactory.getInstance()).get(MemberViewModel::class.java)

        this.viewBinding.fragmentMemberFab.setOnClickListener { this.openMemberActivity() }

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

    private fun openMemberActivity() {
        val intent = Intent(context, MemberDetailActivity::class.java)
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

    companion object {
        fun newInstance() = MemberListFragment
    }



}