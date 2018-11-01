package com.example.andre.verifypresency.register.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.example.andre.verifypresency.BR
import com.example.andre.verifypresency.R

class RegisterForm : BaseObservable() {

    //region Public Fields


    var registerField = RegisterFields()

    //endregion

    //region Private Fields

    private var mRegisterErrorField = RegisterErrorFields()

    //endregion

    //region Public Functions

    fun firstNameValid(setMessage: Boolean): Boolean {

        val firstName = this.registerField.firstName

        when (firstName.isNullOrEmpty()) {

            true -> {

                if (setMessage) {
                    this.mRegisterErrorField.firstName = R.string.ERROR_EMPTY_FIELD
                    notifyPropertyChanged(BR.valid)
                }
                return false
            }

            false -> {
                this.mRegisterErrorField.firstName = null
                notifyPropertyChanged(BR.valid)
                return true
            }
        }

    }

    fun lastNameValid(setMessage: Boolean): Boolean {

        val lastName = this.registerField.lastName

        when (lastName.isNullOrEmpty()) {

            true -> {

                if (setMessage) {
                    this.mRegisterErrorField.lastName = R.string.ERROR_EMPTY_FIELD
                    notifyPropertyChanged(BR.valid)
                }
                return false
            }

            false -> {
                this.mRegisterErrorField.lastName = null
                notifyPropertyChanged(BR.valid)
                return true
            }
        }
    }

    fun orgNameValid(setMessage: Boolean): Boolean {

        val orgName = this.registerField.orgName

        when (orgName.isNullOrEmpty()) {

            true -> {

                if (setMessage) {
                    this.mRegisterErrorField.orgName = R.string.ERROR_EMPTY_FIELD
                    notifyPropertyChanged(BR.valid)
                }
                return false
            }

            false -> {
                this.mRegisterErrorField.orgName = null
                notifyPropertyChanged(BR.valid)
                return true
            }
        }
    }

    fun emailValid(setMessage: Boolean): Boolean {

        //Minimum a@b.c
        val email = this.registerField.email

        when (email != null && email.length > 5) {
            true -> {

                return this.emailValid(email!!, setMessage)

            }

            false -> {
                if (setMessage) {
                    this.mRegisterErrorField.email = R.string.ERROR_TO_SHORT_FIELD
                    notifyPropertyChanged(BR.valid)
                }

                return false
            }
        }
    }

    fun passwordValid(setMessage: Boolean): Boolean {

        val password = this.registerField.password

        return if (password != null && password.length > 6) {

            this.mRegisterErrorField.password = null
            notifyPropertyChanged(BR.valid)
            true
        } else {
            if (setMessage) {
                this.mRegisterErrorField.password = R.string.ERROR_WEAK_PASSWORD
                notifyPropertyChanged(BR.valid)
            }
            false
        }
    }

    fun confirmPasswordValid(setMessage: Boolean): Boolean {

        val confirmPassword = this.registerField.confirmPassword

        when (confirmPassword.isNullOrEmpty()) {

            true -> {
                if (setMessage) {
                    this.mRegisterErrorField.confirmPassword = R.string.ERROR_EMPTY_FIELD
                    notifyPropertyChanged(BR.valid)
                }
                return false

            }

            false -> {

                if (confirmPassword.equals(this.registerField.password)) {
                    this.mRegisterErrorField.confirmPassword = null
                    notifyPropertyChanged(BR.valid)
                    return true

                } else {
                    if (setMessage) {
                        this.mRegisterErrorField.confirmPassword = R.string.ERROR_MATCHING
                        notifyPropertyChanged(BR.valid)
                    }

                    return false
                }

            }
        }

        return true
    }

    val firstNameError: Int?
        @Bindable get() {
            return mRegisterErrorField.firstName
        }

    val lastNameError: Int?
        @Bindable get() {
            return mRegisterErrorField.lastName
        }

    val orgNameError: Int?
        @Bindable get() {
            return mRegisterErrorField.orgName
        }

    val emailError: Int?
        @Bindable get() {
            return mRegisterErrorField.email
        }

    val passwordError: Int?
        @Bindable get() {
            return mRegisterErrorField.password
        }

    val confirmPasswordError: Int?
        @Bindable get() {
            return mRegisterErrorField.confirmPassword
        }

    val valid: Boolean
        @Bindable get() {
            var valid = firstNameValid(false)
            valid = lastNameValid(false) && valid
            valid = orgNameValid(false) && valid
            valid = emailValid(false) && valid
            valid = passwordValid(false) && valid
            valid = confirmPasswordValid(false) && valid

            notifyPropertyChanged(BR.firstNameError)
            notifyPropertyChanged(BR.lastNameError)
            notifyPropertyChanged(BR.orgNameError)
            notifyPropertyChanged(BR.emailError)
            notifyPropertyChanged(BR.passwordError)
            notifyPropertyChanged(BR.confirmPasswordError)

            return valid
        }

    //endregion

    //region Private Functions

    private fun emailValid(email: String, setMessage: Boolean): Boolean {

        val indexOfAt = email.indexOf("@")
        val indexOfDot = email.lastIndexOf(".")

        when (indexOfAt in 1..(indexOfDot - 1) && indexOfDot < email.length - 1) {

            true -> {

                this.mRegisterErrorField.email = null
                notifyPropertyChanged(BR.valid)
                return true
            }

            false -> {
                if (setMessage) {
                    this.mRegisterErrorField.email = R.string.ERROR_BAD_FORMAT
                    notifyPropertyChanged(BR.valid)

                }
                return false
            }
        }

    }

    //endregion

}