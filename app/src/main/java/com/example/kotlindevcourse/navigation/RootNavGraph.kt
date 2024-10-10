package com.example.kotlindevcourse.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.kotlindevcourse.AUTH_GRAPH_ROUTE
import com.example.kotlindevcourse.HomeScreen
import com.example.kotlindevcourse.LoginScreen
import com.example.kotlindevcourse.NotificationScreen
import com.example.kotlindevcourse.ROOT_GRAPH_ROUTE
import com.example.kotlindevcourse.Screen
import com.example.kotlindevcourse.UserProfileScreen


/* Think of it like an App Route Module */
//@ExperimentalMaterialApi

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {

    Log.d("RootNavGraph","Going in NavHost")

    /*https://medium.com/@mathroda/nested-navigation-graph-in-jetpack-compose-with-bottom-navigation-d983c2d4119f*/
    NavHost(
        navController = navHostController,
        startDestination = AUTH_GRAPH_ROUTE,
        route = ROOT_GRAPH_ROUTE
//        startDestination = Screen.Home.route
    ){

        Log.d("RootNavGraph","In NavHost")

        /* Home Nav Graph */
        homeNavGraph(navController = navHostController)

        /* Auth Nav Graph */
        authNavGraph(navController = navHostController)





//        }

    }


}

/*
@Composable
inline fun <reified T: ViewModel>NavBackStackEntry.sharedViewModel(navController: NavController): T{

    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this){

        navController.getBackStackEntry(navGraphRoute)

    }

    return viewModel(parentEntry)


}*/
