package com.example.selfmanagement.uiDisplay

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Checklist
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.SelectAll
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController


val lsGroupTask = listOf(
    "To do", "Doing", "Cancel", "Done"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(navController: NavHostController) {
    var isSelect = remember {
        mutableStateOf(false)
    }
    var indexDisplay = remember {
        mutableIntStateOf(-1)
    }
    var txtSearch = remember {
        mutableStateOf("")
    }
    var isAddTask = remember {
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
                Column(modifier = Modifier.padding(10.dp).fillMaxSize()) {
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
        if (indexDisplay.value == -1) false else lsGroupTask[indexDisplay.value] == lsGroupTask[indexTitle]
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
                    if (indexDisplay.value != indexTitle) indexDisplay.value =
                        indexTitle else indexDisplay.value = -1
                }
                .height(36.dp)
                .background(MaterialTheme.colorScheme.secondary),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (isDisplay) Icons.Rounded.KeyboardArrowDown else Icons.Rounded.ChevronRight,
                contentDescription = "Open ${lsGroupTask[indexTitle]}"
            )
            Text(
                text = lsGroupTask[indexTitle],
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        if (isDisplay) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(color = MaterialTheme.colorScheme.inverseOnSurface),
            ) {
                // TODO("them items cac task va goi CardTask()")
            }
        }
    }
}

@Composable
fun CardTask() {

}