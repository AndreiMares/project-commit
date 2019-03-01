package com.example.andre.verifypresency.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.andre.verifypresency.AppModuleEnum
import com.example.andre.verifypresency.databinding.FragmentCalendarBinding
import com.example.andre.verifypresency.eventdetail.EventDetailActivity
import java.util.*
import com.example.andre.verifypresency.R

class CalendarFragment : Fragment() {

    companion object {
        val EVENT_DATE = "EVENT_DATE"
    }

    private lateinit var viewBinding: FragmentCalendarBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var selectedDate: Date

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container,
                false)

        this.viewBinding.model = ViewModelProviders.of(this, EventModelFactory.getInstance()).get(CalendarViewModel::class.java)

        this.bottomSheetBehavior = BottomSheetBehavior.from(this.viewBinding.snippetBottomBar)

        this.viewBinding.calendarView.setFirstDayOfWeek(Calendar.SUNDAY)
                .setOnDateClickListener {
                    this.configureBottomNavView()
                    this.selectedDate = it
                }

        this.viewBinding.calendarView.update(Calendar.getInstance(Locale.getDefault()))

        return this.viewBinding.root

    }

    override fun onStart() {
        super.onStart()

        this.viewBinding.model?.apply {
            navigation.observe(this@CalendarFragment, Observer<AppModuleEnum> { it ->

                it?.let {

                    when (it) {

                        AppModuleEnum.EVENTLIST -> navigateEventListActivity()
                        AppModuleEnum.NEWEVENT -> navigateCreateEventActivity(selectedDate)

                        else -> {
                        }
                    }
                }
            })
        }
    }

    private fun navigateEventListActivity() {

    }

    private fun navigateCreateEventActivity(date: Date) {
        this.configureBottomNavView()

        val intent = Intent(context, EventDetailActivity::class.java)
        intent.putExtra(EVENT_DATE, date.time)
        startActivity(intent)

    }

    private fun configureBottomNavView() {
        if (this.bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            this.bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            this.bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }
}