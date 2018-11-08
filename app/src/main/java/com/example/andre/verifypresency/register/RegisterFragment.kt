package com.example.andre.verifypresency.register

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.andre.verifypresency.BaseFragment
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.databinding.FragmentRegisterBinding
import com.example.andre.verifypresency.listener.RegisterNavigationListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.android.synthetic.main.fragment_register.*

/**
 *
 */
class RegisterFragment : BaseFragment(), RegisterNavigationListener {

    //region Variables

    private lateinit var mViewDataBinding: FragmentRegisterBinding
    private lateinit var mRegisterViewModel: RegisterViewModel

    //endregion

    //region Override

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.mViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container,
                false)

        return this.mViewDataBinding.root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.mRegisterViewModel = (activity as RegisterActivity).obtainViewModel()

        this.mRegisterViewModel.initialize(this)

        this.mViewDataBinding.model = this.mRegisterViewModel

    }

    override fun onStart() {
        super.onStart()

        this.mRegisterViewModel.getMessage().observe(this, Observer {

            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onRegisterClicked() {

        (activity as RegisterActivity).redirectToLoginScreen()
    }

    //endregion

    companion object {
        fun newInstance(): RegisterFragment = RegisterFragment()
    }
}