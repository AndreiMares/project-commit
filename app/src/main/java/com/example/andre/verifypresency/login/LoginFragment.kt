package com.example.andre.verifypresency.login

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andre.verifypresency.BaseFragment
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.listener.LoginNavigationListener
import com.example.andre.verifypresency.databinding.FragmentLoginBinding
import com.example.andre.verifypresency.main.MainActivity
import com.example.andre.verifypresency.register.RegisterActivity

class LoginFragment : BaseFragment(), LoginNavigationListener {

    private lateinit var mViewBinding: FragmentLoginBinding
    private lateinit var mLoginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.mViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container,
                false)

        return this.mViewBinding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.mLoginViewModel = (activity as LoginActivity).obtainViewModel()

        this.mLoginViewModel.initialize(this)

        this.mViewBinding.model = this.mLoginViewModel

    }


    override fun onSignInClicked() {
        this.navigateToMainActivity()
    }

    override fun onSignUpClicked() {

        this.navigateRegisterActivity()
    }

    /**
     * Navigate to RegisterActivity
     */
    private fun navigateRegisterActivity() {

        val intent = Intent(context, RegisterActivity::class.java)
        startActivity(intent)

    }

    /**
     * Navigate to MainActivity
     */
    private fun navigateToMainActivity() {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
    }
}