package com.example.andre.verifypresency.source.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Member")
data class Member(@PrimaryKey(autoGenerate = true)
                  @ColumnInfo(name = "MemberId")
                  @NotNull
                  var MemberId: Long,
                  @ColumnInfo(name = "FirstName")
                  @NotNull
                  var FirstName: String,
                  @ColumnInfo(name = "LastName")
                  @NotNull
                  var LastName: String,
                  @ColumnInfo(name = "Email")
                  @NotNull
                  var Email: String,
                  @ColumnInfo(name = "PhoneNumber")
                  @NotNull
                  var PhoneNumber: String) : BaseEntity()
{
    @Ignore
    constructor():this(0, "","","","")

}