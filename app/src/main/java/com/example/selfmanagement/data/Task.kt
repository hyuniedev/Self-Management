package com.example.selfmanagement.data

import java.util.Calendar

data class Task(
    var name: String = "",
    var description: String = "",
    var timeStart: Calendar = Calendar.getInstance(),
    var timeEnd: Calendar = Calendar.getInstance(),
    var lsTag: MutableList<Tag> = mutableListOf(),
    var reminder: Calendar = Calendar.getInstance()
)