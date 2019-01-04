package com.example.andre.verifypresency.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.view.View
import android.widget.EditText
import com.example.andre.verifypresency.listener.RegisterNavigationListener
import com.example.andre.verifypresency.form.register.RegisterForm
import com.example.andre.verifypresency.source.remote.register.RegisterDataSource
import com.example.andre.verifypresency.source.remote.register.RegisterRepository

/**
 * ViewModel used for registration layout.
 */
class RegisterViewModel(private val registerRepository: RegisterRepository)
    : ViewModel() {

    //region Public Fields

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
    val dataLoading: ObservableBoolean = ObservableBoolean(false)

    /**
     * Variable used to enable/disable EditText while progressBar is active.
     */
    val enableView: ObservableBoolean = ObservableBoolean(true)

    //endregion

    //region Private Fields

    /**
     * Variable used to go back to Login Activity: true/false.
     */
    private lateinit var mRegisterNavigationListener: RegisterNavigationListener

    private var mMessage = MutableLiveData<String>()

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

            dataLoading.set(true)
            enableView.set(false)

            this.registerRepository.createUser(this.registerForm.registerField, object : RegisterDataSource.SaveUserCallback {

                override fun onUserSaved(message: String) {
                    dataLoading.set(false)
                    enableView.set(true)

                    mMessage.value = message

                    mRegisterNavigationListener.onRegisterClicked()
                }

                override fun onSaveFailed(message: String) {
                    dataLoading.set(false)
                    enableView.set(true)
                    mMessage.value = message

                }
            })
        }
    }

    fun getMessage(): LiveData<String> = this.mMessage

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