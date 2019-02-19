package com.example.andre.verifypresency.form.member

import com.example.andre.verifypresency.main.form.Member

/**
 * Class which encapsulates user inputs.
 */
class MemberFields {

    /**
     * Contains user input text specified for "Name" View
     */
    var name: String? = null


    /**
     * Contains user input text specified for "Email" View
     */
    var email: String? = null

    /**
     * Contains user input text specified for "Phone" View
     */
    var phone: String? = null

    fun convertToMember() = Member(this.name!!, this.email!!, this.phone!!, true)
}