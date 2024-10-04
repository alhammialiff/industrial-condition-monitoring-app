package com.example.kotlindevcourse

//sealed class Screen(val route: String) {
//
//    object Home: Screen(route = "home_screen")
//    object UserProfile: Screen(route = "user_profile_screen")
//
//}

//const val DETAIL_ARGUMENT_KEY = "id"
//const val DETAIL_ARGUMENT_NAME = "name"

const val ROOT_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"
const val HOME_GRAPH_ROUTE = "home"

sealed class Screen(val route: String){

    object Home: Screen(route = "home_screen")
    object Login: Screen(route = "login_screen")
    object Profile: Screen(route = "home_screen/profile_screen")
    object Notification: Screen(route = "home_screen/notification_screen")

    /* TODO - Other route label in future */

}