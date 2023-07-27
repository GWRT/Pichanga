package com.example.pichanga

import java.time.LocalDate
import java.time.LocalTime

data class CalendarState(
    var title : String = "",
    var date : String = LocalDate.now().toString(),
    var time : String = "",
    var repeat : String = "",
    val eventList: List<Event> = emptyList()
)
