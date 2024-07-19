package com.example.kotlindevcourse

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavGraph(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ){

        composable(route = BottomBarScreen.Home.route){
            HomeScreen(
                onUserProfileButtonClicked = {
                    navController.navigate(HomeScreenNav.UserProfile.name)
                },
                onNotificationButtonClicked = {
                    navController.navigate(HomeScreenNav.Notification.name)
                }
            )
        }

        /*composable(route = BottomBarScreen.Home.route){
            *//*asdasd*//*
        }

        composable(route = BottomBarScreen.Home.route){
            *//*asdasdad*//*
        }*/


    }

}
