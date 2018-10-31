package com.example.andre.verifypresency

import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.layout_progressbar.*

open class BaseFragment: Fragment() {

    protected fun startProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }

    protected fun hideProgressBar() {
        if(progressBar?.visibility == View.VISIBLE)
            progressBar.visibility = View.INVISIBLE
    }

    /**
     * Sending a verification email to newly registered user
     */
    protected fun sendVerificationEmail() {
        val user = FirebaseAuth.getInstance().currentUser

        user?.sendEmailVerification()?.addOnCompleteListener { task ->

            if (task.isSuccessful)

                Toast.makeText(context, "An email has been sent for validation", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(context, "Failed to send validation email", Toast.LENGTH_LONG).show()

        }
    }
}