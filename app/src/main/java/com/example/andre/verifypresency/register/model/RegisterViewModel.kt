package com.example.andre.verifypresency.register.model

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.view.View
import android.widget.EditText
import com.example.andre.verifypresency.listener.RegisterNavigationListener

/**
 * ViewModel used for registration layout.
 */
class RegisterViewModel : ViewModel() {

    //region Variables

    /**
     * Validation form variable specified for fragment_registration.xml layout.
     */
    lateinit var registerForm: RegisterForm

    /**
     * OnFocusChangeListener specified for "First Name" View.
     */
    lateinit var onFocusFirstName: View.OnFocusChangeListener

    /**
     * OnFocusChangeListener specified for "Last Name" View.
     */
    lateinit var onFocusLastName: View.OnFocusChangeListener

    /**
     * OnFocusChangeListener specified for "Organization Name" View.
     */
    lateinit var onFocusOrgName: View.OnFocusChangeListener

    /**
     * OnFocusChangeListener specified for "Email" View.
     */
    lateinit var onFocusEmail: View.OnFocusChangeListener

    /**
     * OnFocusChangeListener specified for "Password" View.
     */
    lateinit var onFocusPassword: View.OnFocusChangeListener

    /**
     * OnFocusChangeListener specified for "Confirm Password" View.
     */
    lateinit var onFocusConfirm: View.OnFocusChangeListener

    /**
     * Variable used to show/hide Progress Bar.
     */
    val dataLoading: ObservableBoolean = ObservableBoolean(true)

    /**
     * Variable used to go back to Login Activity: true/false.
     */
    private lateinit var mRegisterNavigationListener: RegisterNavigationListener

    //endregion

    //region Public Functions

    /**
     * Initialize onFocusChangeListeners and RegisterForm.
     */
    fun initialize(listener: RegisterNavigationListener) {

        this.mRegisterNavigationListener = listener

        this.registerForm = RegisterForm()

        this.initOnFocusListeners()

    }

    /**
     * Register Button click handler.
     */
    fun onButtonClick() {
        if (registerForm.valid) {

            dataLoading.set(false)

            this.mRegisterNavigationListener.onRegisterClicked()
        }
    }

    //endregion

    //region Private Functions

    private fun initOnFocusListeners() {

        onFocusFirstName = View.OnFocusChangeListener { v, hasFocus ->

            val et = v as EditText

            if (et.length() == 0 && !hasFocus) {
                this.registerForm.firstNameValid(true)
            }

            if (et.length() > 0 && !hasFocus) {
                this.registerForm.firstNameValid(false)
            }

        }

        onFocusLastName = View.OnFocusChangeListener { v, hasFocus ->

            val et = v as EditText

            if (et.length() == 0 && !hasFocus) {
                this.registerForm.lastNameValid(true)
            }

            if (et.length() > 0 && !hasFocus) {
                this.registerForm.lastNameValid(false)
            }


        }

        onFocusOrgName = View.OnFocusChangeListener { v, hasFocus ->

            val et = v as EditText

            if (et.length() == 0 && !hasFocus) {
                this.registerForm.orgNameValid(true)
            }

            if (et.length() > 0 && !hasFocus)
                this.registerForm.orgNameValid(false)

        }

        onFocusEmail = View.OnFocusChangeListener { v, hasFocus ->

            val et = v as EditText

            if (et.length() == 0 && !hasFocus) {
                this.registerForm.emailValid(true)
            }

            if (et.length() > 0 && !hasFocus)
                this.registerForm.emailValid(true)

        }

        onFocusPassword = View.OnFocusChangeListener { v, hasFocus ->

            val et = v as EditText

            if (et.length() == 0 && !hasFocus) {
                this.registerForm.passwordValid(true)
            }

            if (et.length() > 0 && !hasFocus)
                this.registerForm.passwordValid(true)

        }

        onFocusConfirm = View.OnFocusChangeListener { v, hasFocus ->

            val et = v as EditText

            if (et.length() == 0 && !hasFocus) {
                this.registerForm.confirmPasswordValid(true)
            }

            if (et.length() > 0 && !hasFocus)
                this.registerForm.confirmPasswordValid(true)

        }
    }

    //endregion

}