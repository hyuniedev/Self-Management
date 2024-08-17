package com.example.selfmanagement.bottom_nav

sealed class Screens(val nameScreen: String) {
    data object Task: Screens("Task")
    data object Self: Screens("Self")
    data object Tag: Screens("Tag")
    data object Setting: Screens("Setting")
}