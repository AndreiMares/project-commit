package com.example.andre.verifypresency.register

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.andre.verifypresency.BaseFragment
import com.example.andre.verifypresency.R
import com.example.andre.verifypresency.databinding.FragmentRegisterBinding
import com.example.andre.verifypresency.listener.RegisterNavigationListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.android.synthetic.main.fragment_register.*

/**
 *
 */
class RegisterFragment : BaseFragment(), RegisterNavigationListener {


    //region Variables

    private lateinit var mViewDataBinding: FragmentRegisterBinding
    private lateinit var mRegisterViewModel: RegisterViewModel

    //endregion

    //region Override

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        this.mViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container,
                false)

        return this.mViewDataBinding.root


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.mRegisterViewModel = (activity as RegisterActivity).obtainViewModel()

        this.mRegisterViewModel.initialize(this)

        this.mViewDataBinding.model = this.mRegisterViewModel

    }

    override fun onRegisterClicked() {

        (activity as RegisterActivity).redirectToLoginScreen()
    }

    //endregion

    private fun registerClicked() {

        super.startProgressBar()

        when (this.validateFields(fragment_register_et_email.text.toString()
                , fragment_register_et_password.text.toString()
                , fragment_register_et_confirmation.text.toString())) {

            true -> this.registerNewEmail(fragment_register_et_email.text.toString()
                    , fragment_register_et_password.text.toString())

            false -> super.hideProgressBar()
        }
    }

    /**
     * Registers a new account into FireBase
     */
    private fun registerNewEmail(email: String, password: String) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

            if (task.isSuccessful) {

                this.sendVerificationEmail()

                FirebaseAuth.getInstance().signOut()

//                this.redirectToLoginScreen()

            } else {

                if (task.exception is FirebaseAuthException) {
                    val errorCode = (task.exception as FirebaseAuthException).errorCode

                    when (errorCode) {
                        "ERROR_INVALID_EMAIL" -> {
                            fragment_register_et_email.error = "The email address is badly formatted."
                            fragment_register_et_email.requestFocus()
                        }

                        "ERROR_EMAIL_ALREADY_IN_USE" -> {
                            fragment_register_et_email.error = "The email address is already in use by another account."
                            fragment_register_et_email.requestFocus()
                        }

                        "ERROR_CREDENTIAL_ALREADY_IN_USE" -> {
                            fragment_register_et_email.error = "This credential is already associated with a different user account. "
                            fragment_register_et_email.requestFocus()
                        }

                        "ERROR_WEAK_PASSWORD" -> {
                            fragment_register_et_password.error = "The password is invalid it must 6 characters at least"
                            fragment_register_et_password.requestFocus()
                        }

                        else -> Toast.makeText(context, "Unable to Register", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            super.hideProgressBar()
        }
    }

    /**
     * Returns true if both email and password registerField are valid, otherwise returns false
     */
    private fun validateFields(email: String, password: String, confirmPassword: String): Boolean {

        if (!email.isEmpty()
                && !password.isEmpty()
                && !confirmPassword.isEmpty()) {

            if (password.equals(confirmPassword, false)) {

                return true

            } else {
                Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return false
            }

        } else {
            Toast.makeText(context, "You must fill out all the registerField", Toast.LENGTH_SHORT).show()
            return false
        }

    }

    companion object {
        fun newInstance() = RegisterFragment()
    }
}