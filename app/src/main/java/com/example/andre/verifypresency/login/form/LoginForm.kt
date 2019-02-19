package com.example.andre.verifypresency.login.form

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.example.andre.verifypresency.BR
import com.example.andre.verifypresency.R

class LoginForm : BaseObservable() {

    //region Public Fields

    /**
     * Variable which encapsulates data from user
     */
    var loginField: LoginFields = LoginFields()

    //endregion

    //region Private Fields

    private var mLoginErrorField = LoginErrorFields()

    //endregion

    //region Public Functions

    /**
     * Validation for Email input. Sets an resources error message if email is invalid.
     */
    fun emailValid(setMessage: Boolean): Boolean {

        //Minimum a@b.c
        val email = this.loginField.email

        when (email != null && email.length > 5) {
            true -> {

                return this.emailValid(email!!, setMessage)

            }

            false -> {
                if (setMessage) {
                    this.mLoginErrorField.email = R.string.ERROR_TO_SHORT_FIELD
                    notifyPropertyChanged(BR.valid)
                }

                return false
            }
        }
    }

    /**
     * Validation for Password input. Sets an resources error message if password is invalid.
     */
    fun passwordValid(setMessage: Boolean): Boolean {

        val password = this.loginField.password

        return if (password != null && password.length > 6) {

            this.mLoginErrorField.password = null
            notifyPropertyChanged(BR.valid)
            true
        } else {
            if (setMessage) {
                this.mLoginErrorField.password = R.string.ERROR_WEAK_PASSWORD
                notifyPropertyChanged(BR.valid)
            }
            false
        }
    }

    /**
     * Returns a resource id for setting an error message to TextInputLayout used to wrap-up "Email" View.
     */
    val emailError: Int?
        @Bindable get() {
            return mLoginErrorField.email
        }

    /**
     * Returns a resource id for setting an error message to TextInputLayout used to wrap-up "Password" View.
     */
    val passwordError: Int?
        @Bindable get() {
            return mLoginErrorField.password
        }

    /**
     * Returns true/false which is used to enable or disable "Register Button" View.
     */
    val valid: Boolean
        @Bindable get() {
            var valid = this.emailValid(false)
            valid = this.passwordValid(false) && valid

            notifyPropertyChanged(BR.emailError)
            notifyPropertyChanged(BR.passwordError)

            return valid
        }

    //endregion

    //region Private Functions

    private fun emailValid(email: String, setMessage: Boolean): Boolean {

        val indexOfAt = email.indexOf("@")
        val indexOfDot = email.lastIndexOf(".")

        when (indexOfAt in 1..(indexOfDot - 1) && indexOfDot < email.length - 1) {

            true -> {

                this.mLoginErrorField.email = null
                notifyPropertyChanged(BR.valid)
                return true
            }

            false -> {
                if (setMessage) {
                    this.mLoginErrorField.email = R.string.ERROR_BAD_FORMAT
                    notifyPropertyChanged(BR.valid)

                }
                return false
            }
        }

    }

    //endregion
}