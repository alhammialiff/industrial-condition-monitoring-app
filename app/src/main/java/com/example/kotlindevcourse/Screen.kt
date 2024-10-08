package com.example.kotlindevcourse

//sealed class Screen(val route: String) {
//
//    object Home: Screen(route = "home_screen")
//    object UserProfile: Screen(route = "user_profile_screen")
//
//}

const val TASK_ID = "taskID"
const val STEP_ID = "stepID"

const val ROOT_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"
const val HOME_GRAPH_ROUTE = "home"


sealed class Screen(val route: String){

    object Home: Screen(route = "home_screen")
    object Login: Screen(route = "login_screen")
    object Profile: Screen(route = "home_screen/profile_screen")
    object Notification: Screen(route = "home_screen/notification_screen")
//    object TaskDetail: Screen(route = "home_screen/task_detail")
    object TaskDetail: Screen(route = "home_screen/task_detail/{$TASK_ID}/{$STEP_ID}"){

        fun passTaskIDandStepID(
            TASK_ID: Int,
            STEP_ID: Int
        ): String{
            return "home_screen/task_detail/$TASK_ID/$STEP_ID"
        }

    }
    /* TODO - Other route label in future */

}