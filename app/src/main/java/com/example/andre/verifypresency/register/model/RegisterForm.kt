package com.example.andre.verifypresency.register.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.example.andre.verifypresency.BR
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.source.models.User

/**
 * RegisterForm represent a validation layer for every view from fragment_register.xml layout.
 * RegisterForm is using a two-way data binding.
 */
class RegisterForm : BaseObservable() {

    //region Public Fields

    /**
     * Variable which encapsulates data from user
     */
    var registerField: User = User()

    //endregion

    //region Private Fields

    private var mRegisterErrorField = RegisterErrorFields()

    //endregion

    //region Public Functions

    /**
     * Validation for First Name input. Sets an resources error message if field is empty.
     */
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

    /**
     * Validation for Last Name input. Sets an resources error message if field is empty.
     */
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

    /**
     * Validation for Organization Name input. Sets an resources error message if field is empty.
     */
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

    /**
     * Validation for Email input. Sets an resources error message if email is invalid.
     */
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

    /**
     * Validation for Password input. Sets an resources error message if password is invalid.
     */
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

    /**
     * Validation for Confirm Password input. Sets an resources error message if confirm password is invalid.
     */
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
    }

    /**
     * Returns a resource id for setting an error message to TextInputLayout used to wrap-up "First Name" View.
     */
    val firstNameError: Int?
        @Bindable get() {
            return mRegisterErrorField.firstName
        }

    /**
     * Returns a resource id for setting an error message to TextInputLayout used to wrap-up "Last Name" View.
     */
    val lastNameError: Int?
        @Bindable get() {
            return mRegisterErrorField.lastName
        }

    /**
     * Returns a resource id for setting an error message to TextInputLayout used to wrap-up "Organization Name" View.
     */
    val orgNameError: Int?
        @Bindable get() {
            return mRegisterErrorField.orgName
        }

    /**
     * Returns a resource id for setting an error message to TextInputLayout used to wrap-up "Email" View.
     */
    val emailError: Int?
        @Bindable get() {
            return mRegisterErrorField.email
        }

    /**
     * Returns a resource id for setting an error message to TextInputLayout used to wrap-up "Password" View.
     */
    val passwordError: Int?
        @Bindable get() {
            return mRegisterErrorField.password
        }

    /**
     * Returns a resource id for setting an error message to TextInputLayout used to wrap-up "Confirm Password" View.
     */
    val confirmPasswordError: Int?
        @Bindable get() {
            return mRegisterErrorField.confirmPassword
        }

    /**
     * Returns true/false which is used to enable or disable "Register Button" View.
     */
    val valid: Boolean
        @Bindable get() {
            var valid = this.firstNameValid(false)
            valid = this.lastNameValid(false) && valid
            valid = this.orgNameValid(false) && valid
            valid = this.emailValid(false) && valid
            valid = this.passwordValid(false) && valid
            valid = this.confirmPasswordValid(false) && valid

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