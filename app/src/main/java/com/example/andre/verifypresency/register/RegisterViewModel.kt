package com.example.andre.verifypresency.register

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.view.View
import android.widget.EditText
import com.example.andre.verifypresency.AppModuleEnum
import com.example.andre.verifypresency.SingleLiveEvent
import com.example.andre.verifypresency.register.form.RegisterForm
import com.example.andre.verifypresency.register.remote.RegisterDataSource
import com.example.andre.verifypresency.register.remote.RegisterRepository

/**
 * ViewModel used for registration layout.
 */
class RegisterViewModel(private val registerRepository: RegisterRepository)
    : ViewModel() {

    var registerForm: RegisterForm = RegisterForm()
    lateinit var onFocusFirstName: View.OnFocusChangeListener
    lateinit var onFocusLastName: View.OnFocusChangeListener
    lateinit var onFocusOrgName: View.OnFocusChangeListener
    lateinit var onFocusEmail: View.OnFocusChangeListener
    lateinit var onFocusPassword: View.OnFocusChangeListener
    lateinit var onFocusConfirm: View.OnFocusChangeListener

    //observable fields
    val dataLoading: ObservableBoolean = ObservableBoolean(false)
    val enableView: ObservableBoolean = ObservableBoolean(true)
    val navigation = SingleLiveEvent<Unit>()
    private var mMessage = MutableLiveData<String>()

    init {
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

                    navigation.call()
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

}