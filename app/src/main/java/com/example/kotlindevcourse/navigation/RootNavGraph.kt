package com.example.kotlindevcourse.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.kotlindevcourse.AUTH_GRAPH_ROUTE
import com.example.kotlindevcourse.HomeScreen
import com.example.kotlindevcourse.LoginScreen
import com.example.kotlindevcourse.NotificationScreen
import com.example.kotlindevcourse.ROOT_GRAPH_ROUTE
import com.example.kotlindevcourse.Screen
import com.example.kotlindevcourse.UserProfileScreen


/* Think of it like an App Route Module */
//@ExperimentalMaterialApi

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {

    Log.d("RootNavGraph","Going in NavHost")

    /*https://medium.com/@mathroda/nested-navigation-graph-in-jetpack-compose-with-bottom-navigation-d983c2d4119f*/
    NavHost(
        navController = navHostController,
        startDestination = AUTH_GRAPH_ROUTE,
        route = ROOT_GRAPH_ROUTE
//        startDestination = Screen.Home.route
    ){

        Log.d("RootNavGraph","In NavHost")

        /* Home Nav Graph */
        homeNavGraph(navController = navHostController)

        /* Auth Nav Graph */
        authNavGraph(navController = navHostController)





//        }

    }


}

enum class HomeScreenNav(){
    Home,
    UserProfile,
    Notification,
    Search,
    Schedule,
    Settings
}

enum class RootNav(){
    Home
}