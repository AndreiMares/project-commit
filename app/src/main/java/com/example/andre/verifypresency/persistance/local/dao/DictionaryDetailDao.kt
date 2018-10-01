package com.example.andre.verifypresency.persistance.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.andre.verifypresency.persistance.local.models.DictionaryDetail

@Dao
interface DictionaryDetailDao {

    /**
     * Get a DictionaryDetail by id.

     * @return the dictionaryDetail from the table with a specific id.
     */
    @Query("SELECT * FROM DictionaryDetail WHERE DictionaryDetailId = :id")
    fun getDictionaryDetailById(id: String): DictionaryDetail
}