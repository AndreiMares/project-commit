package com.example.andre.verifypresency.fragments

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.activities.MemberDetailActivity
import com.example.andre.verifypresency.databinding.FragmentMemberBinding

class MemberFragment : Fragment() {

    private lateinit var mViewBinding: FragmentMemberBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.mViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_member, container, false)


        this.mViewBinding.fragmentMemberFab.setOnClickListener { this.openMemberActivity() }

        return this.mViewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun openMemberActivity() {
        val intent = Intent(context, MemberDetailActivity::class.java)
        startActivity(intent)
    }


    companion object {
        fun newInstance() = MemberFragment
    }



}