package com.example.andre.verifypresency.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.view.View
import android.widget.EditText
import com.example.andre.verifypresency.listener.LoginNavigationListener
import com.example.andre.verifypresency.form.login.LoginForm
import com.example.andre.verifypresency.source.remote.login.LoginDataSource
import com.example.andre.verifypresency.source.remote.login.LoginRepository

class LoginViewModel(private val mLoginRepository: LoginRepository)
    : ViewModel() {

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

    /**
     * Variable used to show/hide Progress Bar.
     */
    val dataLoading: ObservableBoolean = ObservableBoolean(false)

    //endregion

    //region Private Fields

    private lateinit var mLoginNavigationListener: LoginNavigationListener

    private var mMessage = MutableLiveData<String>()

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

        if (!this.dataLoading.get())
            this.mLoginNavigationListener.onSignUpClicked()
    }

    /**
     * Register Button click handler.
     */
    fun onSignInButtonClick() {
        if (this.loginForm.valid) {

            dataLoading.set(true)

            this.mLoginRepository.login(this.loginForm.loginField, object : LoginDataSource.LoginCallback {

                override fun onLoginSuccess() {

                    dataLoading.set(false)

                    mLoginNavigationListener.onSignInClicked()
                }

                override fun onLoginFailed(message: String) {
                    dataLoading.set(false)

                    mMessage.value = message
                }

            })


        }
    }

    fun getMessage(): LiveData<String> = this.mMessage

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