package com.example.kotlindevcourse

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*


/* Think of it like an App Route Module */
//@ExperimentalMaterialApi
@Composable
fun RootNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME
//        startDestination = Screen.Home.route
    ){

        composable(
            route = Screen.Home.route
        ){

            HomeScreen(navController = navController)

        }

//        composable(
//            route = Screen.UserProfile.route
//        ){
//
//            UserProfileScreen(navController = navController)
//
//        }

    }


}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
}