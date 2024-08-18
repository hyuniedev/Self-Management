package com.example.selfmanagement.viewmodel

import androidx.lifecycle.ViewModel
import com.example.selfmanagement.data.ContainerTagData
import com.example.selfmanagement.data.Tag
import com.example.selfmanagement.data.Task
import com.example.selfmanagement.data.tagGroup
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskCardModel : ViewModel() {
    private val _uiState = MutableStateFlow(ContainerTagData())
    val uiState : StateFlow<ContainerTagData> = _uiState.asStateFlow()

    fun addTask(task: Task){
        task.lsTag.add(tagGroup[0]) // Add tag TO-DO
        task.lsTag?.forEach { tag ->
            _uiState.value.mapTag[tag]?.add(task)?: mutableSetOf(task)
        }
    }

    fun changeGroup(task:Task, from: Tag, to:Tag){
        task.lsTag?.let {
            it.remove(from)
            it.add(to)
        }
        _uiState.value.mapTag[from]?.remove(task)
        _uiState.value.mapTag[to]?.add(task)?: mutableSetOf(task)
    }

    fun removeTask(task: Task){
        task.lsTag?.forEach { tag ->
            _uiState.value.mapTag[tag]?.remove(task)
        }
    }
}