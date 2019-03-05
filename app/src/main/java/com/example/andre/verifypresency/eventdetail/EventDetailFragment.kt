package com.example.andre.verifypresency.eventdetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.navOptions
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.databinding.FragmentEventDetailBinding

class EventDetailFragment : Fragment() {

    private lateinit var viewBinding: FragmentEventDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_detail, container,
                false)

        this.viewBinding.model = (activity as EventDetailActivity).obtainViewModel(EventDetailViewModel::class.java)


        return this.viewBinding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.viewBinding.model?.apply {
            navigation.observe(this@EventDetailFragment, Observer {

                (activity as EventDetailActivity).onBackPressed()
            })

        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        this.viewBinding.fragmentEventDetailBtn.setOnClickListener {
            findNavController(this).navigate(R.id.add_members_action, null, options)
        }

        this.setAdapter()
    }

    override fun onResume() {
        super.onResume()

        this.viewBinding.model?.prepareSelectedMembersList()
    }

    private fun setAdapter() {
        val viewModel = this.viewBinding.model

        if (viewModel != null) {
            val listAdapter = EventDetailListAdapter(ArrayList(0))
            val viewManager = LinearLayoutManager(context)

            this.viewBinding.fragmentRecycleView.adapter = listAdapter
            this.viewBinding.fragmentRecycleView.layoutManager = viewManager

        }
    }

//    companion object {
//
//        fun newInstance(timeInMilisec: Long) = EventDetailFragment().apply {
//            arguments = Bundle().apply {
//                putLong(EVENT_DATE, timeInMilisec)
//            }
//        }
//
//        val EVENT_DETAIL_FRAGMENT = "EVENT_DETAIL_FRAGMENT"
//    }
}