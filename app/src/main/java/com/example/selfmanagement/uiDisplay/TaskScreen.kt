package com.example.selfmanagement.uiDisplay

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


val lsGroupTask = listOf(
    "To do", "Doing", "Cancel", "Done"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(navController: NavHostController) {
    var isSearch = remember {
        mutableStateOf(false)
    }
    var isSelect = remember {
        mutableStateOf(false)
    }
    var indexDisplay = remember {
        mutableIntStateOf(-1)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "My Tasks") },
                actions = {
                    IconButton(onClick = {
                        isSearch.value = !isSearch.value
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search"
                        )
                    }
                    IconButton(
                        onClick = {
                            isSelect.value = !isSelect.value
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Checklist,
                            contentDescription = "Select"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.inverseOnSurface
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
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
            // Tim kiem

            // Danh sach task
            LazyColumn (
                modifier = Modifier.padding(6.dp)
            ){
                items(lsGroupTask.size){index->
                    GroupToDo(indexTitle = index, indexDisplay = indexDisplay)
                }
            }
        }
    }
}

@Composable
fun GroupToDo(indexTitle: Int, indexDisplay: MutableIntState){
    val isDisplay = if(indexDisplay.value==-1) false else lsGroupTask[indexDisplay.value]== lsGroupTask[indexTitle]
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 6.dp)
            .animateContentSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {if(indexDisplay.value!=indexTitle) indexDisplay.value=indexTitle else indexDisplay.value=-1}) {
                Icon(
                    imageVector = if(isDisplay) Icons.Rounded.KeyboardArrowDown else Icons.Rounded.ChevronRight, 
                    contentDescription = "Open ${lsGroupTask[indexTitle]}"
                )
            }
            Text(text = lsGroupTask[indexTitle])
        }
        if(isDisplay){
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(bottom = 10.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(color = MaterialTheme.colorScheme.inverseOnSurface),
            ){
                // TODO("them items cac task va goi CardTask()")
            }
        }
    }
}

@Composable
fun CardTask(){

}