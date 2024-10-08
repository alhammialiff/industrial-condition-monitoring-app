package com.example.kotlindevcourse.navigation

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.kotlindevcourse.HOME_GRAPH_ROUTE
import com.example.kotlindevcourse.HomeScreen
import com.example.kotlindevcourse.NotificationScreen
import com.example.kotlindevcourse.STEP_ID
import com.example.kotlindevcourse.Screen
import com.example.kotlindevcourse.TASK_ID
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

                /*onTaskCardClicked = {
                    navController.navigate(Screen.TaskDetail.route)
                }*/

                /* [Note]
                *  Because we need to pass arguments into Task Details,
                *  we need to send navController down the composable,
                *  and only invoke .navigate inside the clickable
                *  task card
                * */
                onTaskCardClicked = navController

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
        composable(
            route = Screen.TaskDetail.route,
            arguments = listOf(
                /* [To pass to this route] TASK_ID */
                navArgument(TASK_ID){
                    type = NavType.IntType
                },

                /* [To pass to this route] STEP_ID */
                navArgument(STEP_ID){
                    type = NavType.IntType
                }
            )
        ){
            /* [Log] Arguments for Task Detail Screen */
            Log.d("Home Nav Graph - Task Details - Args - ", it.arguments?.getInt(TASK_ID).toString())

            /* Invoke Task Detail Screen
            * TODO; Rename this to Task Detail Start Screen
            * */
            TaskDetailScreen(
                navController = navController,
                TASK_ID = it.arguments?.getInt(TASK_ID, 0)
            )

        }

    }


}