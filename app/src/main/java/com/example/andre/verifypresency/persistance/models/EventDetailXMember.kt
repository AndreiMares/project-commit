package com.example.andre.verifypresency.persistance.models

import android.arch.persistence.room.*
import org.jetbrains.annotations.NotNull

@Entity(tableName = "EventDetailXMember",
        foreignKeys = arrayOf(
                ForeignKey(entity = EventDetail::class,
                        parentColumns = arrayOf("EventDetailId"),
                        childColumns = arrayOf("EventDetailId")),
                ForeignKey(entity = Member::class,
                        parentColumns = arrayOf("MemberId"),
                        childColumns = arrayOf("MemberId"))))

data class EventDetailXMember(@PrimaryKey(autoGenerate = true)
                              @NotNull
                              @ColumnInfo(name = "EventDetailXMemberId")
                              var EventDetailXMemberId: Long,
                              @NotNull
                              @ColumnInfo(name = "EventDetailId")
                              var EventDetailId: Long,
                              @NotNull
                              @ColumnInfo(name = "MemberId")
                              var MemberId: Long) : BaseEntity() {

    @Ignore
    constructor() : this(0, 0, 0)
}