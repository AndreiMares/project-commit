package com.example.andre.verifypresency.source.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity(tableName = "Event")
data class Event(@PrimaryKey(autoGenerate = true)
            @ColumnInfo(name = "EventId")
            @NotNull
            var EventId: Long,
            @ColumnInfo(name = "ScheduleTime")
            var ScheduleTime: Date?) : BaseEntity()

{
    @Ignore
    constructor():this(0,null)

}