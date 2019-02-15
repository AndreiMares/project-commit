package com.example.andre.verifypresency.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.activities.MemberDetailActivity
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


    override fun onResume() {
        super.onResume()
    }

    private fun openMemberActivity() {
        val intent = Intent(context, MemberDetailActivity::class.java)
        startActivity(intent)
    }


    companion object {
        fun newInstance() = MemberListFragment
    }



}