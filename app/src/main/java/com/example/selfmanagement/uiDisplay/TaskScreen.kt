package com.example.selfmanagement.uiDisplay

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.selfmanagement.data.Task
import com.example.selfmanagement.data.tagGroup
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


val lsGroupTask = listOf(
    "To do", "Doing", "Cancel", "Done"
)

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen() {
    val indexDisplay = remember {
        mutableIntStateOf(-1)
    }
    val isAddTask = remember {
        mutableStateOf(false)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "My Tasks", fontWeight = FontWeight.Bold, fontSize = 26.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.inverseOnSurface
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                isAddTask.value = true
            }) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add new task",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Danh sach task
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
                    .zIndex(0f)
            ) {
                items(lsGroupTask.size) { index ->
                    GroupToDo(indexTitle = index, indexDisplay = indexDisplay)
                }
            }
        }
        if(isAddTask.value){
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
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Composable
fun GroupToDo(indexTitle: Int, indexDisplay: MutableIntState) {
    val isDisplay =
        if (indexDisplay.intValue == -1) false else lsGroupTask[indexDisplay.intValue] == lsGroupTask[indexTitle]
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 6.dp)
            .animateContentSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    if (indexDisplay.intValue != indexTitle) indexDisplay.intValue =
                        indexTitle else indexDisplay.intValue = -1
                }
                .height(36.dp)
                .background(MaterialTheme.colorScheme.secondary),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (isDisplay) Icons.Rounded.KeyboardArrowDown else Icons.Rounded.ChevronRight,
                contentDescription = "Open ${lsGroupTask[indexTitle]}",
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = lsGroupTask[indexTitle],
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
        if (isDisplay) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clip(RoundedCornerShape(6.dp)),
            ) {
                // TODO("them items cac task va goi CardTask()")
                val tmpTask: Task = Task(
                    name = "nau com",
                    id = 1,
                    description = "Nau com an toi",
                    lsTag = tagGroup.toMutableSet()
                )
                CardTask(task = tmpTask)
            }
        }
    }
}

@Composable
fun CardTask(task: Task) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.inverseOnSurface)
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        LazyRow {
            task.lsTag?.forEach {
                // TODO("Add Composable Tags")
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = task.name,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = task.description,
            fontSize = 18.sp,
            fontStyle = FontStyle.Italic,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(14.dp))
        RowTime(title = "Start:", time = getCurrentDateTime(task.timeStart.time))
        RowTime(title = "End:", time = getCurrentDateTime(task.timeEnd.time))
        RowTime(title = "Reminder:", time = getCurrentDateTime(task.reminder.time))
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.align(Alignment.End)
        ) {
            OutlinedButton(onClick = {}) {
                Text(text = "Move")
            }
            Spacer(modifier = Modifier.width(20.dp))
            OutlinedButton(
                onClick = {}
            ) {
                Text(text = "Edit")
            }
        }
    }
}

@Composable
private fun RowTime(title: String, time: String){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = title,
            fontSize = 18.sp
        )
        Text(
            text = time,
            fontSize = 18.sp,
            fontStyle = FontStyle.Italic
        )
    }
}

fun getCurrentDateTime(time: Date): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy, HH:mm", Locale.getDefault())
    return formatter.format(time)
}