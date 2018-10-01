package com.example.andre.verifypresency.persistance.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.example.andre.verifypresency.persistance.DateConverter
import com.example.andre.verifypresency.persistance.local.dao.*
import com.example.andre.verifypresency.persistance.local.models.*

@Database(entities = arrayOf(Dictionary::class, DictionaryDetail::class, Event::class, EventDetail::class
        , EventDetailXMember::class, Member::class), version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun dictionaryDao(): DictionaryDao
    abstract fun dictionaryDetailDao(): DictionaryDetailDao
    abstract fun eventDao(): EventDao
    abstract fun eventDetailDao(): EventDetailDao
    abstract fun eventDetailXMemberDao(): EventDetailXMemberDao
    abstract fun memberDao(): MemberDao


    companion object {

        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "VerifyPresenceDatabase.db")
                        .build()
    }
}