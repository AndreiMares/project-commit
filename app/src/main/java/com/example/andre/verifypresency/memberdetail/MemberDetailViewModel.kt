package com.example.andre.verifypresency.memberdetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.view.View
import android.widget.EditText
import com.example.andre.verifypresency.memberdetail.form.MemberForm
import com.example.andre.verifypresency.main.remote.MemberDataSource
import com.example.andre.verifypresency.main.remote.MemberRepository

class MemberDetailViewModel(private val memberRepository: MemberRepository)
    : ViewModel() {


    lateinit var memberForm: MemberForm

    lateinit var onFocusName: View.OnFocusChangeListener
    lateinit var onFocusEmail: View.OnFocusChangeListener
    lateinit var onFocusPhoneNumber: View.OnFocusChangeListener

    //varibales used to automatically update views
    val dataLoading: ObservableBoolean = ObservableBoolean(false)
    val enableView: ObservableBoolean = ObservableBoolean(true)
    val fabVisibilityView: ObservableBoolean = ObservableBoolean(true)

    private var mMessage = MutableLiveData<String>()

    init {

        this.memberForm = MemberForm()

        this.initOnFocusListeners()
    }

    //region Private Functions

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

    //endregion

    fun fabVisibility(visible: Boolean) {
        this.fabVisibilityView.set(visible)
    }

    fun onButtonClick() {
        if (this.memberForm.valid) {

            dataLoading.set(true)
            enableView.set(false)

            this.memberRepository.saveMember(this.memberForm.memberField.convertToMember(), object : MemberDataSource.SaveCallback {

                override fun onSaveSuccess(message: String) {
                    dataLoading.set(false)
                    enableView.set(true)

                    mMessage.value = message

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

}