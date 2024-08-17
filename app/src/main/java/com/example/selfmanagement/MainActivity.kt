package com.example.selfmanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.selfmanagement.bottom_nav.BottomNavigationBar
import com.example.selfmanagement.bottom_nav.Screens
import com.example.selfmanagement.ui.theme.SelfManagementTheme
import com.example.selfmanagement.uiDisplay.SelfScreen
import com.example.selfmanagement.uiDisplay.SettingScreen
import com.example.selfmanagement.uiDisplay.TagScreen
import com.example.selfmanagement.uiDisplay.TaskScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SelfManagementTheme {
                MainScreen()
            }
        }
    }
}

@Preview
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var itemNavSelected = rememberSaveable {
        mutableStateOf(Screens.Self.nameScreen)
    }
    Scaffold(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize(),
        bottomBar = { BottomNavigationBar(itemNavSelected,navController)}
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavHost(navController = navController, startDestination = Screens.Self.nameScreen){
                composable(Screens.Self.nameScreen){ SelfScreen(navController = navController)}
                composable(Screens.Task.nameScreen){ TaskScreen(navController = navController)}
                composable(Screens.Tag.nameScreen){ TagScreen(navController = navController)}
                composable(Screens.Setting.nameScreen){ SettingScreen(navController = navController)}
            }
        }
    }
}