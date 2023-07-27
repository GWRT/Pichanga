package com.example.pichanga.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int ?= null,
    val title : String,
    val date : String,
    val time : String,
    val repeat : String
)
