package com.example.andre.verifypresency.persistance.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.andre.verifypresency.persistance.models.Dictionary
import com.example.andre.verifypresency.persistance.models.DictionaryDetail
import io.reactivex.Flowable

@Dao
interface DictionaryDetailDao {

    /**
     * Get a DictionaryDetail by id.

     * @return the dictionaryDetail from the table with a specific id.
     */
    @Query("SELECT * FROM DictionaryDetail WHERE DictionaryDetailId = :id")
    fun getDictionaryDetailById(id: String): Flowable<DictionaryDetail>
}