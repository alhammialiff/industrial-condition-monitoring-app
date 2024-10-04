package com.example.kotlindevcourse.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.kotlindevcourse.HOME_GRAPH_ROUTE
import com.example.kotlindevcourse.HomeScreen
import com.example.kotlindevcourse.NotificationScreen
import com.example.kotlindevcourse.Screen
import com.example.kotlindevcourse.UserProfileScreen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Screen.Home.route,
        route = HOME_GRAPH_ROUTE
    ){

        composable(
            route = Screen.Home.route
        ){

            HomeScreen(

                onUserProfileButtonClicked = {
                    navController.navigate(Screen.Profile.route)
                },

                onNotificationButtonClicked = {
                    navController.navigate(Screen.Notification.route)
                }

            )


        }

        /* User Profile Route */
        composable(route = Screen.Profile.route){
            UserProfileScreen(navController = navController)
        }

        /* Notification Route */
        composable(route = Screen.Notification.route){
            NotificationScreen(navController = navController)
        }

        /* TODO; Search Route */

        /* TODO; Schedule Route */

        /* TODO; Settings Route */

    }


}