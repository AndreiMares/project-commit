package com.example.andre.verifypresency.main

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.databinding.FragmentEventBinding
import com.example.andre.verifypresency.event.EventActivity
import kotlinx.android.synthetic.main.fragment_event.*


class EventFragment : Fragment() {

    private lateinit var mViewBinding: FragmentEventBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.mViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_event, container,
                false)


        this.mViewBinding.fragmentEventFab.setOnClickListener { this.openEventActivity() }

        return this.mViewBinding.root

    }

    fun openEventActivity() {
        val intent = Intent(context, EventActivity::class.java)
        startActivity(intent)
    }

    companion object {
        fun newInstance() = EventFragment
    }

}