//package com.example.andre.verifypresency.source.models
//
//import android.arch.persistence.room.*
//import org.jetbrains.annotations.NotNull
//
//@Entity(tableName = "EventDetail",
//        foreignKeys = arrayOf(
////                ForeignKey(entity = Event::class,
////                        parentColumns = arrayOf("EventId"),
////                        childColumns = arrayOf("EventId")),
//                ForeignKey(entity = DictionaryDetail::class,
//                        parentColumns = arrayOf("DictionaryDetailId"),
//                        childColumns = arrayOf("DictionaryDetailId"))))
//
//data class EventDetail(@PrimaryKey(autoGenerate = true)
//                       @ColumnInfo(name = "EventDetailId")
//                       @NotNull
//                       var EventDetailId: Long,
//                       @ColumnInfo(name = "Title")
//                       @NotNull
//                       var Title: String,
//                       @ColumnInfo(name = "Location")
//                       @NotNull
//                       var Location: String,
//                       @ColumnInfo(name = "OrganizationName")
//                       @NotNull
//                       var OrganizationName: String,
//                       @ColumnInfo(name = "DictionaryDetailId")
//                       @NotNull
//                       var DictionaryDetailId: Long,
//                       @ColumnInfo(name = "EventId")
//                       @NotNull
//                       var EventId: Long) : BaseEntity() {
//
//    @Ignore
//    constructor() : this(0, "", "", "", 0, 0)
//
//}