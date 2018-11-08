package com.example.andre.verifypresency.source.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.andre.verifypresency.source.models.Dictionary

@Dao
interface DictionaryDao {

    /**
     * Get a Dictionary by id.

     * @return the dictionary from the table with a specific id.
     */
    @Query("SELECT * FROM Dictionary WHERE DictionaryId = :id")
    fun getDictionaryById(id: String): Dictionary

    /**
     * Insert a dictionary in the database. If the dictionary already exists, replace it.

     * @param dictionary the dictionary to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDictionary(dictionary: Dictionary)

    /**
     * Delete all dictionaries.
     */
    @Query("DELETE FROM Dictionary")
    fun deleteAllDictionaries()
}