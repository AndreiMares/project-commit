package com.example.andre.verifypresency.login

import android.arch.lifecycle.ViewModel
import android.view.View
import android.widget.EditText
import com.example.andre.verifypresency.listener.LoginNavigationListener
import com.example.andre.verifypresency.login.model.LoginForm

class LoginViewModel : ViewModel() {

    //region Public Fields

    /**
     * Validation form variable specified for fragment_login.xml layout.
     */
    lateinit var loginForm: LoginForm

    /**
     * OnFocusChangeListener specified for "Email" View.
     */
    lateinit var onFocusEmail: View.OnFocusChangeListener

    /**
     * OnFocusChangeListener specified for "Password" View.
     */
    lateinit var onFocusPassword: View.OnFocusChangeListener

    //endregion

    //region Private Fields

    private lateinit var mLoginNavigationListener: LoginNavigationListener

    //endregion

    //region Public Functions

    /**
     * Initialize onFocusChangeListeners and loginForm.
     */
    fun initialize(listener: LoginNavigationListener) {

        this.mLoginNavigationListener = listener

        this.loginForm = LoginForm()

        this.initOnFocusListeners()

    }


    /**
     * Navigate to RegisterActivity when user press Register Button.
     */
    fun onRegisterButtonClick() {

        this.mLoginNavigationListener.onSignUpClicked()
    }

    /**
     * Register Button click handler.
     */
    fun onSignInButtonClick() {
        if (this.loginForm.valid) {

//            dataLoading.set(false)

            this.mLoginNavigationListener.onSignInClicked()
        }
    }

    //endregion

    //region Private Functions

    private fun initOnFocusListeners() {

        onFocusEmail = View.OnFocusChangeListener { v, hasFocus ->

            val et = v as EditText

            if (et.length() == 0 && !hasFocus) {
                this.loginForm.emailValid(true)
            }

            if (et.length() > 0 && !hasFocus)
                this.loginForm.emailValid(true)

        }

        onFocusPassword = View.OnFocusChangeListener { v, hasFocus ->

            val et = v as EditText

            if (et.length() == 0 && !hasFocus) {
                this.loginForm.passwordValid(true)
            }

            if (et.length() > 0 && !hasFocus)
                this.loginForm.passwordValid(true)

        }

        
    }

    //endregion
}