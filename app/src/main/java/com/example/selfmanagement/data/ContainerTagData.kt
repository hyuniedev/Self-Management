package com.example.selfmanagement.data

private fun getMapTagGroup() : MutableMap<Tag,MutableSet<Task>>{
    val tmpMap : MutableMap<Tag,MutableSet<Task>> = mutableMapOf()
    repeat(4){index ->
        tmpMap[tagGroup[index]] = mutableSetOf()
    }
    return tmpMap
}

data class ContainerTagData(
    val mapTag: MutableMap<Tag,MutableSet<Task>> = getMapTagGroup()
)