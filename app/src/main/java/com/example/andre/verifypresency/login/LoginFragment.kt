package com.example.andre.verifypresency.login

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.databinding.FragmentLoginBinding
import com.example.andre.verifypresency.activities.MainActivity
import com.example.andre.verifypresency.register.RegisterActivity

class LoginFragment : Fragment(), LoginNavigationListener {

    private lateinit var mViewBinding: FragmentLoginBinding
    private lateinit var mLoginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.mViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container,
                false)

        this.mViewBinding.fragmentLoginIvImage.setOnClickListener { this.autocomplete() }

        return this.mViewBinding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.mLoginViewModel = (activity as LoginActivity).obtainViewModel()

        this.mLoginViewModel.initialize(this)

        this.mViewBinding.model = this.mLoginViewModel

    }

    override fun onStart() {
        super.onStart()
        this.mLoginViewModel.getMessage().observe(this, Observer {

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
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

    private fun autocomplete() {
        this.mViewBinding.fragmentLoginEtEmail.setText("andrei.mares06@gmail.com")
        this.mViewBinding.fragmentLoginEtPassword.setText("21andreimares")


    }

    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
    }
}