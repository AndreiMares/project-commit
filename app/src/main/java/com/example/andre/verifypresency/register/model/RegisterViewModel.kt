package com.example.andre.verifypresency.register.model

import android.arch.lifecycle.ViewModel
import android.databinding.Bindable
import android.view.View
import android.widget.EditText

class RegisterViewModel : ViewModel() {

    lateinit var registerForm: RegisterForm
    lateinit var onFocusEmail: View.OnFocusChangeListener
    lateinit var onFocusPassword: View.OnFocusChangeListener


    fun initialize() {

        this.registerForm = RegisterForm()

        onFocusEmail = View.OnFocusChangeListener { v, hasFocus ->

            var et = v as EditText

            if (et.length() > 0 && !hasFocus)
                this.registerForm.emailValid(true)


        }

        onFocusPassword = View.OnFocusChangeListener { v, hasFocus ->

            var et = v as EditText

            if (et.length() > 0 && !hasFocus)
                this.registerForm.passwordValid(true)


        }
    }

    val emailOnFocusChangeListener: View.OnFocusChangeListener
        get() {
            return onFocusEmail
        }

    val passwordOnFocusChangeListener: View.OnFocusChangeListener
        get() {
            return onFocusPassword
        }
}