package com.example.andre.verifypresency.persistance.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.andre.verifypresency.persistance.models.Member

@Dao
interface MemberDao {

    /**
     * Get a Member by id.

     * @return the member from the table with a specific id.
     */
    @Query("SELECT * FROM Member WHERE MemberId = :id")
    fun getMemberById(id: String): Member
}