package com.example.pichanga.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event : EventEntity)

    @Query("SELECT * FROM EventEntity")
    suspend fun getEvents() : List<EventEntity>
}