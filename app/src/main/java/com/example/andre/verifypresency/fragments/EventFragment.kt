package com.example.andre.verifypresency.fragments

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.databinding.FragmentEventBinding
import com.example.andre.verifypresency.activities.EventDetailActivity


class EventFragment : Fragment() {

    private lateinit var mViewBinding: FragmentEventBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.mViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_event, container,
                false)


        this.mViewBinding.fragmentEventFab.setOnClickListener { this.openEventActivity() }

        return this.mViewBinding.root

    }

    private fun openEventActivity() {
        val intent = Intent(context, EventDetailActivity::class.java)
        startActivity(intent)
    }

    companion object {
        fun newInstance() = EventFragment
    }

}