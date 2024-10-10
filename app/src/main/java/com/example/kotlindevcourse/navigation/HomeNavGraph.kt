package com.example.kotlindevcourse.navigation

import android.util.Log
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.kotlindevcourse.AUTH_GRAPH_ROUTE
import com.example.kotlindevcourse.HOME_GRAPH_ROUTE
import com.example.kotlindevcourse.HomeScreen
import com.example.kotlindevcourse.NotificationScreen
import com.example.kotlindevcourse.ROOT_GRAPH_ROUTE
import com.example.kotlindevcourse.STEP_ID
import com.example.kotlindevcourse.Screen
import com.example.kotlindevcourse.TASK_ID
import com.example.kotlindevcourse.TASK_TUTORIAL_ROUTE
import com.example.kotlindevcourse.TaskDetailStartScreen
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

                onTaskCardClicked = navController

                /* [Note]
                *  Because we need to pass arguments into Task Details,
                *  we need to send navController down the composable,
                *  and only invoke .navigate inside the clickable
                *  task card
                * */
//                onTaskCardClicked = {
//                    navController.navigate(Screen.TaskDetailStart.route)
//                }

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

        /* (Sub-link 3) Task Card Route */
        composable(
            route = Screen.TaskDetailStart.route,
            arguments = listOf(
                 /*[To pass to this route] TASK_ID */
                navArgument(TASK_ID){
                    type = NavType.IntType
                },

                 /*[To pass to this route] STEP_ID */
                navArgument(STEP_ID){
                    type = NavType.IntType
                }
            )
        ){
            /* [Log] Arguments for Task Detail Screen */
//            Log.d("Home Nav Graph - Task Details - Args - ", it.arguments?.getInt(TASK_ID).toString())
//            Log.d("Home Nav Graph - Task Details - Screen.TaskDetailStep - ", Screen.TaskDetailStep.route)

            /* Invoke Task Detail Screen */
            TaskDetailStartScreen(
                navController = navController,
                TASK_ID = it.arguments?.getInt(TASK_ID, 0)
            )


            Log.d("Home Nav Graph - Nested NavHost Task Tutorial","In NavHost")

            /*Task Tutorial Nav Graph */
            taskTutorialNavGraph(navController = navController)

        }


        /* TODO; Search Route */

        /* TODO; Schedule Route */

        /* TODO; Settings Route */

        /* TODO; Tab Content (Task Card) */

    }


}