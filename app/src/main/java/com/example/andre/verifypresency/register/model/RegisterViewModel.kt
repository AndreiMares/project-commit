package com.example.andre.verifypresency.register.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.view.View
import android.widget.EditText

class RegisterViewModel : ViewModel() {

    lateinit var registerForm: RegisterForm
    lateinit var onFocusFirstName: View.OnFocusChangeListener
    lateinit var onFocusLastName: View.OnFocusChangeListener
    lateinit var onFocusOrgName: View.OnFocusChangeListener
    lateinit var onFocusEmail: View.OnFocusChangeListener
    lateinit var onFocusPassword: View.OnFocusChangeListener
    lateinit var onFocusConfirm: View.OnFocusChangeListener

    val dataLoading = ObservableBoolean(true)
    val navigateToActivity = MutableLiveData<Boolean>()

    fun initialize() {

        this.registerForm = RegisterForm()

        this.initOnFocusListeners()

    }

    fun onButtonClick(){
        if(registerForm.valid){

            dataLoading.set(false)


            navigateToActivity.value = true
//            TimeUnit.SECONDS.sleep(3)
//
//            dataLoading.set(true)
        }
    }

    private fun initOnFocusListeners(){

        onFocusFirstName = View.OnFocusChangeListener { v, hasFocus ->

            val et = v as EditText

            if (et.length() == 0 && !hasFocus){
                this.registerForm.firstNameValid(true)
            }

            if (et.length() > 0 && !hasFocus){
                this.registerForm.firstNameValid(false)
            }

        }

        onFocusLastName = View.OnFocusChangeListener { v, hasFocus ->

            val et = v as EditText

            if (et.length() == 0 && !hasFocus){
                this.registerForm.lastNameValid(true)
            }

            if (et.length() > 0 && !hasFocus){
                this.registerForm.lastNameValid(false)
            }


        }

        onFocusOrgName = View.OnFocusChangeListener { v, hasFocus ->

            val et = v as EditText

            if (et.length() == 0 && !hasFocus){
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