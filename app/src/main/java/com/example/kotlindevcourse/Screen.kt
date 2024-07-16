package com.example.kotlindevcourse

sealed class Screen(val route: String) {

    object Home: Screen(route = "home_screen")
    object Overview: Screen(route = "overview_screen")

}