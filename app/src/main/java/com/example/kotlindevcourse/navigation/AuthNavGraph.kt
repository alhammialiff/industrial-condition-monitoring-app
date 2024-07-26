package com.example.kotlindevcourse.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.kotlindevcourse.AUTH_GRAPH_ROUTE
import com.example.kotlindevcourse.LoginScreen
import com.example.kotlindevcourse.Screen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
){

    navigation(
        startDestination = Screen.Login.route,
        route = AUTH_GRAPH_ROUTE
    ){

        /* Auth Route */
        composable(
            route = Screen.Login.route
        ){

            /* Login Screen */
            LoginScreen(navController = navController)

        }

    }


}