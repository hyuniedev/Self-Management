package com.example.selfmanagement.data

import java.util.Calendar

data class Task(
    val id: Long = System.currentTimeMillis(),
    var name: String = "",
    var description: String = "",
    var timeStart: Calendar = Calendar.getInstance(),
    var timeEnd: Calendar = Calendar.getInstance(),
    var lsTag: MutableSet<Tag> = mutableSetOf(),
    var reminder: Calendar = Calendar.getInstance()
)