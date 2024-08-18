package com.example.selfmanagement.bottom_nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentInd
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.outlined.AssignmentInd
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Tag
import androidx.compose.material.icons.outlined.Task
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.selfmanagement.data.ItemNav


private val itemsNav = listOf(
    ItemNav(
        title = Screens.Self.nameScreen,
        iconSelected = Icons.Filled.AssignmentInd,
        iconUnselect = Icons.Outlined.AssignmentInd
    ),
    ItemNav(
        title = Screens.Task.nameScreen,
        iconSelected = Icons.Filled.Task,
        iconUnselect = Icons.Outlined.Task
    ),
    ItemNav(
        title = Screens.Tag.nameScreen,
        iconSelected = Icons.Filled.Tag,
        iconUnselect = Icons.Outlined.Tag
    ),
    ItemNav(
        title = Screens.Setting.nameScreen,
        iconSelected = Icons.Filled.Settings,
        iconUnselect = Icons.Outlined.Settings
    )
)

@Composable
fun BottomNavigationBar(itemNavSelected: MutableState<String>, navController: NavHostController) {
    NavigationBar {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            itemsNav.forEachIndexed { _, itemNav ->
                NavigationBarItem(
                    selected = itemNavSelected.value == itemNav.title,
                    onClick = {
                        itemNavSelected.value = itemNav.title
                        navController.navigate(itemNav.title){
                            popUpTo(0)
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (itemNavSelected.value == itemNav.title) itemNav.iconSelected else itemNav.iconUnselect,
                            contentDescription = itemNav.title,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    label = {
                        Text(text = itemNav.title, color = MaterialTheme.colorScheme.onBackground)
                    }
                )
            }
        }
    }
}