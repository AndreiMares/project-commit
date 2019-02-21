package com.example.andre.verifypresency.memberdetail.form

import com.example.andre.verifypresency.main.form.Member

/**
 * Class which encapsulates user inputs.
 */
class MemberFields {

    var memberId: String = ""

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

    val convertToMember: Member
        get() {
            val member = Member(this.name!!, this.email!!, this.phone!!, true)
            member.memberId = this.memberId
            return member
        }
}