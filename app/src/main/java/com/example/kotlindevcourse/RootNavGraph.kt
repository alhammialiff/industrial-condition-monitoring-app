package com.example.kotlindevcourse

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*


/* Think of it like an App Route Module */
//@ExperimentalMaterialApi
@Composable
fun RootNavGraph(

) {
    val navController: NavHostController = rememberNavController()
    /*https://medium.com/@mathroda/nested-navigation-graph-in-jetpack-compose-with-bottom-navigation-d983c2d4119f*/
    NavHost(
        navController = navController,
        startDestination = HomeScreenNav.Home.name
//        startDestination = Screen.Home.route
    ){


        composable(
            route = HomeScreenNav.Home.name
        ){

            HomeScreen(

                onUserProfileButtonClicked = {
                    navController.navigate(HomeScreenNav.UserProfile.name)
                },

                onNotificationButtonClicked = {
                    navController.navigate(HomeScreenNav.Notification.name)
                },


            )

        }

        /* Create a nested NavGraph (via navigation(...)) for quick access bars
        *  within Home Screen.
        *
        *   This might be the solution of the error whenever
        *   User Profile quick access button is clicked -:
        *
        *   '...NavDeepLink...Navigation graph has not been set for NavController'
        *
        * */
        navigation(startDestination = HomeScreenNav.Home.name, route = "home"){

            /* User Profile Route */
            composable(route = HomeScreenNav.UserProfile.name){
                UserProfileScreen(navController = navController)
            }

            /* Notification Route */
            composable(route = HomeScreenNav.Notification.name){
                NotificationScreen(navController = navController)
            }

            /* TODO; Search Route */

            /* TODO; Schedule Route */

            /* TODO; Settings Route */

        }

    }


}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
//    const val USERPROFILE = "user_profile"
}
//
//sealed class HomeScreenNav(val route: String){
//    object UserProfile: HomeScreenNav(route = "user_profile")
//    object Home: HomeScreenNav(route = "home_screen")
//}

enum class HomeScreenNav(){
    Home,
    UserProfile,
    Notification,
    Search,
    Schedule,
    Settings
}