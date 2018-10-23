package com.example.andre.verifypresency.register.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.example.andre.verifypresency.BR
import com.example.andre.verifypresency.R

class RegisterForm : BaseObservable() {

    var registerField = RegisterFields()
    var registerErrorField = RegisterErrorFields()


//    @Bindable
//    fun valid(): Boolean{
//        var valid = passwordValid(false)
//        notifyPropertyChanged(BR.passwordError)
//
//        return valid
//    }

    fun emailValid(setMessage: Boolean): Boolean {

        return true
    }

    fun passwordValid(setMessage: Boolean): Boolean {

        val password = this.registerField.password

        return if (password != null && password.length > 6) {

            this.registerErrorField.password = null
            notifyPropertyChanged(BR.passwordError)
            true
        } else {
            if (setMessage) {
                this.registerErrorField.password = R.string.ERROR_WEAK_PASSWORD
                notifyPropertyChanged(BR.passwordError)
            }
            false
        }
    }

    val emailError: Int?
    @Bindable get() {
        return registerErrorField.email
    }

    val passwordError: Int?
        @Bindable get() {
            return registerErrorField.password
        }
}