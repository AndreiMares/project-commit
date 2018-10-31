package com.example.andre.verifypresency.login.model

import android.arch.lifecycle.ViewModel
import com.example.andre.verifypresency.listener.LoginNavigationListener

class LoginViewModel : ViewModel() {


    /**
     * Validation form variable specified for fragment_login.xml layout.
     */
    lateinit var loginForm: LoginForm

    private lateinit var mLoginNavigationListener: LoginNavigationListener


    /**
     * Initialize onFocusChangeListeners and RegisterForm.
     */
    fun initialize(listener: LoginNavigationListener) {

        this.mLoginNavigationListener = listener

        this.loginForm = LoginForm()

//        this.initOnFocusListeners()

    }


    /**
     * Navigate to RegisterActivity when user press Register Button.
     */
    fun onRegisterButtonClick() {

        this.mLoginNavigationListener.onSignUpClicked()
    }
}