package com.example.andre.verifypresency.main

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.databinding.FragmentEventListBinding
import com.example.andre.verifypresency.eventdetail.EventDetailActivity


class EventListFragment : Fragment() {

    private lateinit var viewBinding: FragmentEventListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_list, container,
                false)


        this.viewBinding.fragmentEventFab.setOnClickListener { this.openEventActivity() }

        return this.viewBinding.root

    }

    private fun openEventActivity() {
        val intent = Intent(context, EventDetailActivity::class.java)
        startActivity(intent)
    }

    companion object {
        fun newInstance() = EventListFragment
    }

}