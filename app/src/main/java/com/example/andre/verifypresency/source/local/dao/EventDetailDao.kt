//package com.example.andre.verifypresency.source.local.dao
//
//import android.arch.persistence.room.Dao
//import android.arch.persistence.room.Query
//import com.example.andre.verifypresency.source.models.EventDetail
//
//@Dao
//interface EventDetailDao {
//
//    /**
//     * Get a EventDetail by id.
//
//     * @return the eventDetail from the table with a specific id.
//     */
//    @Query("SELECT * FROM EventDetail WHERE EventDetailId = :id")
//    fun getEventDetailById(id: String): EventDetail
//}