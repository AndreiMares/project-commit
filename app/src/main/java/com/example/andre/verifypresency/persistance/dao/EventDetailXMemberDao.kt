package com.example.andre.verifypresency.persistance.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.andre.verifypresency.persistance.models.EventDetailXMember

@Dao
interface EventDetailXMemberDao {

    /**
     * Get a EventDetailXMember by id.

     * @return the eventDetailXMember from the table with a specific id.
     */
    @Query("SELECT * FROM EventDetailXMember WHERE EventDetailXMemberId = :id")
    fun getEventDetailXMemberById(id: String): EventDetailXMember
}