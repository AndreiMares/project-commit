package com.example.andre.verifypresency.source.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull

//@Entity(tableName = "Member")
data class Member(/*@PrimaryKey(autoGenerate = true)
                  var memberId: Long,*/
        var name: String,
        var email: String,
        var phoneNumber: String,
        var active: Boolean) : BaseEntity() {

}