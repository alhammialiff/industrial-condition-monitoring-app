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
import com.example.kotlindevcourse.TASK_TUTORIAL_ROUTE
import com.example.kotlindevcourse.TaskDetailCompleteScreen
import com.example.kotlindevcourse.TaskDetailStartScreen
import com.example.kotlindevcourse.TaskDetailStepScreen
import com.example.kotlindevcourse.UserProfileScreen

fun NavGraphBuilder.taskTutorialNavGraph(
    navController: NavHostController
){
    // Home Navigation Graph
    navigation(
        startDestination = Screen.TaskDetailStart.route,
        route = TASK_TUTORIAL_ROUTE
    ){

        Log.d("Task Tutorial Graph","In Nav Graph")

        /* Task Detail Start Screen Route */
        composable(
            route = Screen.TaskDetailStart.route,
            arguments = listOf(
                /* [To pass to this route] TASK_ID */
                /*navArgument(TASK_ID){
                    type = NavType.IntType
                    defaultValue = 0
                },*/
                navArgument(TASK_ID){
                    type = NavType.StringType
                    defaultValue = "null"
                },

        )){

            Log.d("Task Tutorial Graph","Routing to Task Detail Start")

            TaskDetailStartScreen(
                navController = navController,
                TASK_ID = it.arguments?.getString(TASK_ID,"null")
            )

        }

        /* Task Detail Step Screen Route */
        composable(
            route = Screen.TaskDetailStep.route,
            arguments = listOf(
                /* [To pass to this route] TASK_ID */
                navArgument(TASK_ID){
                    type = NavType.StringType
                    defaultValue = "null"
                },

                /* [To pass to this route] STEP_ID */
                navArgument(STEP_ID){
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ){

            Log.d("Task Tutorial Graph","Routing to Task Detail STEP")

            /* [Log] Arguments for Task Detail Step Screen */
            Log.d("Task Tutorial Route - Task Details - Args - ", it.arguments?.getInt(TASK_ID).toString())

            /* Invoke Task Detail Step Screen */
            TaskDetailStepScreen(
                navController = navController,
                TASK_ID = it.arguments?.getString(TASK_ID, "null"),
                STEP_ID = it.arguments?.getInt(STEP_ID, 0),
            )


        }

        /* Task Detail Complete Screen Route */
        composable(
            route = Screen.TaskDetailComplete.route,
            arguments = listOf(
                /* [To pass to this route] TASK_ID */
                navArgument(TASK_ID){
                    type = NavType.StringType
                    defaultValue = "null"
                }
            )
        ){

            /* Invoke Task Detail Step Screen */
            TaskDetailCompleteScreen(
                navController = navController,
                TASK_ID = it.arguments?.getString(TASK_ID, "null")
            )

        }

    }


}