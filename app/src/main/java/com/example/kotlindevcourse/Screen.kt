package com.example.kotlindevcourse

sealed class Screen(val route: String) {

    object Home: Screen(route = "home_screen")
    object UserProfile: Screen(route = "user_profile_screen")

}