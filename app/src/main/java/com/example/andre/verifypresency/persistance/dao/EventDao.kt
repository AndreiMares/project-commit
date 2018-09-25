package com.example.andre.verifypresency.persistance.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.andre.verifypresency.persistance.models.Event

@Dao
interface EventDao {

    /**
     * Get a Event by id.

     * @return the event from the table with a specific id.
     */
    @Query("SELECT * FROM Event WHERE EventId = :id")
    fun getEventById(id: String): Event
}