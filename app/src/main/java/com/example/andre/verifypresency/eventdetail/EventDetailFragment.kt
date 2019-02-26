package com.example.andre.verifypresency.eventdetail

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.databinding.FragmentEventDetailBinding
import com.example.andre.verifypresency.main.CalendarFragment.Companion.EVENT_DATE

class EventDetailFragment : Fragment() {

    private lateinit var viewBinding: FragmentEventDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_detail, container,
                false)


//        this.viewBinding.fragmentEventFab.setOnClickListener { this.openEventActivity() }

        return this.viewBinding.root

    }


    companion object {

        fun newInstance(timeInMilisec: Long) = EventDetailFragment().apply {
            arguments = Bundle().apply {
                putLong(EVENT_DATE, timeInMilisec)
            }
        }

        val EVENT_DETAIL_FRAGMENT = "EVENT_DETAIL_FRAGMENT"
    }
}