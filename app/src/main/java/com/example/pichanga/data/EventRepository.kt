package com.example.pichanga.data

import com.example.pichanga.Event

class EventRepository(
    private val eventDao : EventDao
) {
    suspend fun getEvents() : List<Event>{
        val entities = eventDao.getEvents()
        return entities.map{
            Event(title = it.title, date = it.date, time = it.time, repeat = it.repeat)
        }
    }

    suspend fun insertEvent(event : Event){
        val entity = EventEntity(title = event.title, date = event.date, time = event.time, repeat = event.repeat)
        eventDao.insertEvent(entity)
    }
}