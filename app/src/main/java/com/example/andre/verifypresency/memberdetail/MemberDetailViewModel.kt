package com.example.andre.verifypresency.memberdetail

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.view.View
import android.widget.EditText
import com.example.andre.verifypresency.SingleLiveEvent
import com.example.andre.verifypresency.memberdetail.form.MemberForm
import com.example.andre.verifypresency.main.remote.MemberDataSource
import com.example.andre.verifypresency.main.remote.MemberRepository

class MemberDetailViewModel(private val memberRepository: MemberRepository)
    : ViewModel() {

    var memberForm: MemberForm

    lateinit var onFocusName: View.OnFocusChangeListener
    lateinit var onFocusEmail: View.OnFocusChangeListener
    lateinit var onFocusPhoneNumber: View.OnFocusChangeListener

    //observable views
    val dataLoading: ObservableBoolean = ObservableBoolean(false)
    val fabVisibilityView: ObservableBoolean = ObservableBoolean(true)
    val navigation = SingleLiveEvent<Unit>()
    val snackbarMessage = SingleLiveEvent<String>()

    init {

        this.memberForm = MemberForm()

        this.initOnFocusListeners()
    }

    /**
     * Initialize onFocusChangeListeners and form.
     */
    private fun initOnFocusListeners() {

        this.onFocusName = View.OnFocusChangeListener { v, hasFocus ->

            val et = v as EditText

            if (et.length() == 0 && !hasFocus) {
                this.memberForm.nameValid(true)
            }

            if (et.length() > 0 && !hasFocus) {
                this.memberForm.nameValid(false)
            }

        }

        this.onFocusEmail = View.OnFocusChangeListener { v, hasFocus ->

            val et = v as EditText

            if (et.length() == 0 && !hasFocus) {
                this.memberForm.emailValid(true)
            }

            if (et.length() > 0 && !hasFocus)
                this.memberForm.emailValid(true)

        }

        this.onFocusPhoneNumber = View.OnFocusChangeListener { v, hasFocus ->

            val et = v as EditText

            if (et.length() == 0 && !hasFocus) {
                this.memberForm.phoneNumberValid(true)
            }

            if (et.length() > 0 && !hasFocus) {
                this.memberForm.phoneNumberValid(false)
            }
        }

    }

    fun onButtonClick() {
        if (this.memberForm.valid) {

            dataLoading.set(true)

            this.memberRepository.saveMember(this.memberForm.memberField.convertToMember(), object : MemberDataSource.SaveCallback {

                override fun onSaveSuccess(message: String) {
                    dataLoading.set(false)

                    navigation.call()

                }

                override fun onSaveFailed(message: String) {
                    dataLoading.set(false)

                    snackbarMessage.value = message

                }
            })
        }
    }
}