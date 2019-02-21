package com.example.andre.verifypresency.memberdetail

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.view.View
import android.widget.EditText
import com.example.andre.verifypresency.SingleLiveEvent
import com.example.andre.verifypresency.main.form.Member
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
    var onEdit = ObservableField(false)
    val navigation = SingleLiveEvent<Unit>()
    val snackbarMessage = SingleLiveEvent<String>()

    init {

        this.memberForm = MemberForm()
        this.initOnFocusListeners()
    }

    fun loadMember(edit: Boolean?, name: String?) {

        this.onEdit.set(edit)
        this.dataLoading.set(true)

        this.memberRepository.getMember(name!!, object : MemberDataSource.LoadSingleCallback<Member> {
            override fun onSuccess(member: Member) {

                dataLoading.set(false)

                memberForm.memberField.memberId = member.memberId
                memberForm.memberField.name = member.name
                memberForm.memberField.email = member.email
                memberForm.memberField.phone = member.phoneNumber

                memberForm.notifyChange()
            }

            override fun onFailed(message: String?) {
            }

        })

    }

    fun onButtonClick() {


        when (this.onEdit.get()) {

            true -> this.updateMember()
            false -> this.saveMember()
        }

    }

    private fun saveMember() {
        this.dataLoading.set(true)

        if (this.memberForm.valid) {
            this.memberRepository.saveMember(this.memberForm.memberField.convertToMember, object : MemberDataSource.SaveCallback {

                override fun onSuccess(message: String) {
                    dataLoading.set(false)

                    navigation.call()

                }

                override fun onFailed(message: String) {
                    dataLoading.set(false)

                    snackbarMessage.value = message

                }
            })
        }
    }

    private fun updateMember() {
        this.dataLoading.set(true)

        this.memberRepository.updateMember(this.memberForm.memberField.convertToMember, object : MemberDataSource.UpdateCallback {

            override fun onSuccess() {
                dataLoading.set(false)
                navigation.call()

            }

            override fun onFailed(message: String?) {
                dataLoading.set(false)
                snackbarMessage.value = message

            }
        })

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
}