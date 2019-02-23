package com.example.andre.verifypresency.register

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.databinding.FragmentRegisterBinding

/**
 *
 */
class RegisterFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container,
                false)

        this.registerViewModel = (activity as RegisterActivity).obtainViewModel()

        this.viewDataBinding.model = this.registerViewModel

        return this.viewDataBinding.root

    }

    override fun onStart() {
        super.onStart()

        this.registerViewModel.getMessage().observe(this, Observer {

            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })

        this.registerViewModel.apply {
            navigation.observe(this@RegisterFragment, Observer<Unit> { it ->

                (activity as RegisterActivity).redirectToLoginScreen()
            })
        }
    }

    companion object {
        fun newInstance(): RegisterFragment = RegisterFragment()
    }
}