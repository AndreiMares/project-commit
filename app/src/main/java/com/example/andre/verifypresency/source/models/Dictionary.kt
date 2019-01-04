//package com.example.andre.verifypresency.source.models
//
//import android.arch.persistence.room.ColumnInfo
//import android.arch.persistence.room.Entity
//import android.arch.persistence.room.PrimaryKey
//import org.jetbrains.annotations.NotNull
//
//@Entity(tableName = "Dictionary")
//class Dictionary(@PrimaryKey(autoGenerate = true)
//                 @ColumnInfo(name = "DictionaryId")
//                 @NotNull
//                 var DictionaryId: Long,
//                 @ColumnInfo(name = "Name")
//                 @NotNull
//                 var Name: String) : BaseEntity() {
//    constructor() : this(0, "")
//
//}