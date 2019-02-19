package com.example.andre.verifypresency.login

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.view.View
import android.widget.EditText
import com.example.andre.verifypresency.AppModuleEnum
import com.example.andre.verifypresency.SingleLiveEvent
import com.example.andre.verifypresency.login.form.LoginForm
import com.example.andre.verifypresency.login.remote.LoginDataSource
import com.example.andre.verifypresency.login.remote.LoginRepository

class LoginViewModel(private val mLoginRepository: LoginRepository)
    : ViewModel() {

    var loginForm: LoginForm = LoginForm()
    lateinit var onFocusEmail: View.OnFocusChangeListener
    lateinit var onFocusPassword: View.OnFocusChangeListener

    //observable fields
    val dataLoading: ObservableBoolean = ObservableBoolean(false)
    val snackBarMessage = SingleLiveEvent<String>()
    val navigation = SingleLiveEvent<AppModuleEnum>()

    init {

        this.initOnFocusListeners()

    }

    fun onButtonClicked(appModuleEnum: AppModuleEnum): Unit =
            when (appModuleEnum) {

                AppModuleEnum.LOGIN -> {
                    this.onSignInButtonClick()
                }

                AppModuleEnum.REGISTER -> {
                    navigation.value = AppModuleEnum.REGISTER
                }
            }

    /**
     * Register Button click handler.
     */
    private fun onSignInButtonClick() {
        if (this.loginForm.valid) {

            dataLoading.set(true)

            this.mLoginRepository.login(data = this.loginForm.loginField, callBack = object : LoginDataSource.LoginCallback {

                override fun onLoginSuccess() {

                    dataLoading.set(false)

                    navigation.value = AppModuleEnum.LOGIN
                }

                override fun onLoginFailed(message: String) {
                    dataLoading.set(false)

                    snackBarMessage.value = message
                }
            })
        }
    }

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
}