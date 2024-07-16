package com.example.kotlindevcourse

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){

        composable(
            route = Screen.Home.route
        ){

            HomeScreen()

        }

        composable(
            route = Screen.UserProfile.route
        ){

//            MainActivity()

        }


    }


}