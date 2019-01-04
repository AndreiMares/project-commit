package com.example.andre.verifypresency.form.member

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.example.andre.verifypresency.BR
import com.example.andre.verifypresency.R

class MemberForm : BaseObservable() {

    /**
     * Variable which encapsulates data from user
     */
    var memberField: MemberFields = MemberFields()

    private var mMemberErrorField = MemberErrorFields()

    /**
     * Returns a resource id for setting an error message to TextInputLayout used to wrap-up "Name" View.
     */
    val nameError: Int?
        @Bindable get() {
            return mMemberErrorField.name
        }

    /**
     * Returns a resource id for setting an error message to TextInputLayout used to wrap-up "Email" View.
     */
    val emailError: Int?
        @Bindable get() {
            return mMemberErrorField.email
        }

    /**
     * Returns a resource id for setting an error message to TextInputLayout used to wrap-up "Phone Number" View.
     */
    val phoneNumberError: Int?
        @Bindable get() {
            return mMemberErrorField.phone
        }

    /**
     * Returns true/false which is used to enable or disable "Save Button" View.
     */
    val valid: Boolean
        @Bindable get() {
            var valid = this.nameValid(false)
            valid = this.phoneNumberValid(false) && valid
            valid = this.emailValid(false) && valid

            return valid
        }

    //region Public Functions

    /**
     * Validation for First Name input. Sets an resources error message if field is empty.
     */
    fun nameValid(setMessage: Boolean): Boolean {

        val firstName = this.memberField.name

        when (firstName.isNullOrEmpty()) {

            true -> {

                if (setMessage) {
                    this.mMemberErrorField.name = R.string.ERROR_EMPTY_FIELD
                    notifyPropertyChanged(BR.nameError)
                }
                return false
            }

            false -> {
                this.mMemberErrorField.name = null
                notifyPropertyChanged(BR.nameError)
                return true
            }
        }

    }

    /**
     * Validation for Last Name input. Sets an resources error message if field is empty.
     */
    fun phoneNumberValid(setMessage: Boolean): Boolean {

        val lastName = this.memberField.phone

        when (lastName.isNullOrEmpty()) {

            true -> {

                if (setMessage) {
                    this.mMemberErrorField.phone = R.string.ERROR_EMPTY_FIELD
                    notifyPropertyChanged(BR.phoneNumberError)
                }
                return false
            }

            false -> {
                this.mMemberErrorField.phone = null
                notifyPropertyChanged(BR.phoneNumberError)
                return true
            }
        }
    }

    /**
     * Validation for Email input. Sets an resources error message if email is invalid.
     */
    fun emailValid(setMessage: Boolean): Boolean {

        //Minimum a@b.c
        val email = this.memberField.email

        when (email != null && email.length > 5) {
            true -> {

                return this.emailValid(email!!, setMessage)

            }

            false -> {
                if (setMessage) {
                    this.mMemberErrorField.email = R.string.ERROR_TO_SHORT_FIELD
                    notifyPropertyChanged(BR.emailError)
                }

                return false
            }
        }
    }

    //endregion

    //region Private Functions

    private fun emailValid(email: String, setMessage: Boolean): Boolean {

        val indexOfAt = email.indexOf("@")
        val indexOfDot = email.lastIndexOf(".")

        when (indexOfAt in 1..(indexOfDot - 1) && indexOfDot < email.length - 1) {

            true -> {

                this.mMemberErrorField.email = null
                notifyPropertyChanged(BR.emailError)
                return true
            }

            false -> {
                if (setMessage) {
                    this.mMemberErrorField.email = R.string.ERROR_BAD_FORMAT
                    notifyPropertyChanged(BR.emailError)

                }
                return false
            }
        }

    }

    //endregion
}