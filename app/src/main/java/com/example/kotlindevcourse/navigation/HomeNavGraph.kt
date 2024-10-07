package com.example.kotlindevcourse.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.kotlindevcourse.HOME_GRAPH_ROUTE
import com.example.kotlindevcourse.HomeScreen
import com.example.kotlindevcourse.NotificationScreen
import com.example.kotlindevcourse.Screen
import com.example.kotlindevcourse.TaskDetailScreen
import com.example.kotlindevcourse.UserProfileScreen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
){
    // Home Navigation Graph
    navigation(
        startDestination = Screen.Home.route,
        route = HOME_GRAPH_ROUTE
    ){

        // (Start Route Declaration) Home Route
        composable(
            route = Screen.Home.route
        ){

            HomeScreen(

                onUserProfileButtonClicked = {
                    navController.navigate(Screen.Profile.route)
                },

                onNotificationButtonClicked = {
                    navController.navigate(Screen.Notification.route)
                },

                onTaskCardClicked = {
                    navController.navigate(Screen.TaskDetail.route)
                }

            )


        }

        /* (Sub-link 1) User Profile Route */
        composable(route = Screen.Profile.route){
            UserProfileScreen(navController = navController)
        }

        /* (Sub-link 2) Notification Route */
        composable(route = Screen.Notification.route){
            NotificationScreen(navController = navController)
        }

        /* TODO; Search Route */

        /* TODO; Schedule Route */

        /* TODO; Settings Route */

        /* TODO; Tab Content (Task Card) */
        composable(route = Screen.TaskDetail.route){
            TaskDetailScreen(navController = navController)
        }

    }


}