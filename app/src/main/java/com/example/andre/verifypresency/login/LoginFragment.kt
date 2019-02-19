package com.example.andre.verifypresency.login

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.andre.verifypresency.AppModuleEnum
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.databinding.FragmentLoginBinding
import com.example.andre.verifypresency.main.MainActivity
import com.example.andre.verifypresency.register.RegisterActivity

class LoginFragment : Fragment(){

    private lateinit var viewBinding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container,
                false)

        this.loginViewModel = (activity as LoginActivity).obtainViewModel()

        this.viewBinding.model = this.loginViewModel

        this.viewBinding.fragmentLoginIvImage.setOnClickListener { this.autocomplete() }

        return this.viewBinding.root

    }

    override fun onStart() {
        super.onStart()

        this.loginViewModel.apply {
            snackBarMessage.observe(this@LoginFragment, Observer<String> { it ->

                it?.let { Snackbar.make(viewBinding.root, it, Snackbar.LENGTH_SHORT).show() }
            })
        }

        this.loginViewModel.apply {
            navigation.observe(this@LoginFragment, Observer<AppModuleEnum> { it ->

                it?.let {

                    when (it) {

                        AppModuleEnum.REGISTER -> navigateRegisterActivity()
                        AppModuleEnum.LOGIN -> navigateToMainActivity()
                    }
                }
            })
        }
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
        this.viewBinding.fragmentLoginEtEmail.setText("andrei.mares06@gmail.com")
        this.viewBinding.fragmentLoginEtPassword.setText("21andreimares")


    }

    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
    }
}