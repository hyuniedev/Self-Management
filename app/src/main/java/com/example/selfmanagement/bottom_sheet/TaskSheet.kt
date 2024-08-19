package com.example.selfmanagement.bottom_sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.selfmanagement.data.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomTaskSheet(task: Task = Task(), isAddTask: MutableState<Boolean>){
    ModalBottomSheet(
        onDismissRequest = {
            isAddTask.value = false
        }
    ) {
        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()) {
            Text(
                text = "Add Task",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
            )
        }
    }
}