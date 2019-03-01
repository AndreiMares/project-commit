package com.example.andre.verifypresency.eventdetail

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.databinding.RowEventDetailListItemBinding
import com.example.andre.verifypresency.main.form.Member

class EventDetailListAdapter(
        private var memberList: List<Member>
) : RecyclerView.Adapter<EventDetailListViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): EventDetailListViewHolder {

        val inflater = LayoutInflater.from(p0.context)

        val itemBinding = DataBindingUtil
                .inflate<RowEventDetailListItemBinding>(inflater, R.layout.row_event_detail_list_item, p0, false
                )

        return EventDetailListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(p0: EventDetailListViewHolder, p1: Int) {

        //get a single repository item based by position
        val member = this.memberList[p1]
        p0.bind(member)

    }

    override fun getItemCount(): Int = this.memberList.size

    fun loadList(repoList: List<Member>) = this.setAdapterList(repoList)

    private fun setAdapterList(repoList: List<Member>) {

        this.memberList = repoList
        notifyDataSetChanged()

    }

}