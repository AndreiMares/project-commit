package com.example.andre.verifypresency.eventdetail

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.databinding.FragmentFilterMemberListBinding


class FilterMemberListFragment : Fragment() {

    private lateinit var viewBinding: FragmentFilterMemberListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        this.viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter_member_list, container,
                false)

        this.viewBinding.model = (activity as EventDetailActivity).obtainViewModel(EventDetailViewModel::class.java)

        // Inflate the layout for this fragment
        return this.viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.setAdapter()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.viewBinding.model?.apply {
            navigation.observe(this@FilterMemberListFragment, Observer {
//                NavHostFragment.findNavController(this@FilterMemberListFragment).navigate(R.id.eventDetailFragment)

                (activity as EventDetailActivity).onBackPressed()
            })

        }
    }

    override fun onResume() {
        super.onResume()

        this.viewBinding.model?.prepareLoadingList()
    }

    private fun setAdapter() {
        val viewModel = this.viewBinding.model

        if (viewModel != null) {
            val listAdapter = FilterMemberListAdapter(ArrayList(0), viewModel)
            val viewManager = LinearLayoutManager(context)

            this.viewBinding.fragmentRecycleView.adapter = listAdapter
            this.viewBinding.fragmentRecycleView.layoutManager = viewManager

        }
    }


}
