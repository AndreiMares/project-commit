package com.example.andre.verifypresency.persistance.local.models

import android.arch.persistence.room.ColumnInfo
import java.util.*

open class BaseEntity(
        @ColumnInfo(name = "SyncLastChangeTime")
        var SyncLastChangeTime: Date?,
        @ColumnInfo(name = "SyncLastChangUserId")
        var SyncLastChangUserId: Long,
        @ColumnInfo(name = "SyncStatus")
        var SyncStatus: Byte = 0)

{
        constructor():this(null,0,0)

}