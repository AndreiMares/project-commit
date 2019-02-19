package com.example.andre.verifypresency.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.source.models.Member
import com.example.andre.verifypresency.viewholder.MemberListViewHolder
import com.example.andre.verifypresency.viewmodel.MemberViewModel

class MemberListAdapter(
        private var memberList: List<Member>,
        private val viewModel: MemberViewModel
) : RecyclerView.Adapter<MemberListViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MemberListViewHolder {

        val inflater = LayoutInflater.from(p0.context)

        val rowProductListItemBinding = DataBindingUtil
                .inflate<com.example.andre.verifypresency.databinding.RowMemberListItemBinding>(inflater, R.layout.row_member_list_item, p0, false
                )

        return MemberListViewHolder(rowProductListItemBinding)
    }

    override fun onBindViewHolder(p0: MemberListViewHolder, p1: Int) {

        //get a single repository item based by position
        val product = this.memberList[p1]

//        val userActionListener = object : ProductItemUserActionListener {
//            override fun onProductClicked(product: Product) {
//
//                viewModel.navigateToDetail.value = product
//
//            }
//
//            override fun onDeleteClicked(product: Product) {
//                viewModel.deleteProduct(product)
//            }
//
//
//        }


        p0.bind(product)


//
//        //get the action when user clicks on a repository


//        //set the internal layout variables
//        with(this.rowProductListItemBinding) {
//            model = product
//            listener = userActionListener
//
//        }
    }

    override fun getItemCount(): Int = this.memberList.size

    fun loadList(repoList: List<Member>) = this.setAdapterList(repoList)

    private fun setAdapterList(repoList: List<Member>) {

        this.memberList = repoList
        notifyDataSetChanged()

    }


}