package com.example.andre.verifypresency.persistance.local.models

import android.arch.persistence.room.*
import org.jetbrains.annotations.NotNull


@Entity(tableName = "DictionaryDetail",
        foreignKeys = arrayOf(ForeignKey(entity = Dictionary::class,
                parentColumns = arrayOf("DictionaryId"),
                childColumns = arrayOf("DictionaryId"))))

data class DictionaryDetail(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "DictionaryDetailId")
        @NotNull
        var DictionaryDetailId: Long,
        @ColumnInfo(name = "Name")
        @NotNull
        var Name: String,
        @ColumnInfo(name = "DictionaryId")
        @NotNull
        var DictionaryId: Long,
        @ColumnInfo(name = "Valid")
        @NotNull
        var Valid: Byte) : BaseEntity() {

    @Ignore
    constructor() : this(0, "", 0, 0)

}