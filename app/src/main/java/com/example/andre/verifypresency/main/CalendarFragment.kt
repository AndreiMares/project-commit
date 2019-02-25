package com.example.andre.verifypresency.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {


    private lateinit var viewBinding: FragmentCalendarBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container,
                false)



        return this.viewBinding.root

    }
}