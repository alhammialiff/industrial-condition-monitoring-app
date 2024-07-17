package com.example.kotlindevcourse

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*


/* Think of it like an App Route Module */
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

            /* Stop here: Do I need to pass navController?*/
            HomeScreen(navController = navController)

        }

        composable(
            route = Screen.UserProfile.route
        ){

//            MainActivity()
            UserProfileScreen()

        }


    }


}