package com.example.kotlindevcourse

//sealed class Screen(val route: String) {
//
//    object Home: Screen(route = "home_screen")
//    object UserProfile: Screen(route = "user_profile_screen")
//
//}

const val TASK_ID = "taskID"
const val STEP_ID = "stepID"
const val USERNAME = "username"

const val ROOT_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"
const val HOME_GRAPH_ROUTE = "home"
const val TASK_TUTORIAL_ROUTE = "task_tutorial"


sealed class Screen(val route: String){

    object Home: Screen(route = "home_screen")
    object Login: Screen(route = "login_screen")
    object Profile: Screen(route = "home_screen/profile_screen/{$USERNAME}")
    object Notification: Screen(route = "home_screen/notification_screen")
//    object TaskDetailStart: Screen(route = "home_screen/task_detail")

    object TaskDetailStart: Screen(route = "home_screen/task_detail/{$TASK_ID}"){

        fun passTaskIDandStepID(
            TASK_ID: Int
        ): String{
            return "home_screen/task_detail/$TASK_ID"
        }

    }

    object TaskDetailStep: Screen(route = "home_screen/task_detail/{$TASK_ID}/step/{$STEP_ID}"){

        fun passTaskIDandStepID(
            TASK_ID: Int,
            STEP_ID: Int
        ): String{
            return "home_screen/task_detail/$TASK_ID/step/$STEP_ID"
        }

    }

    object TaskDetailComplete: Screen(route = "home_screen/task_detail/{$TASK_ID}/complete"){

        fun passTaskIDandStepID(
            TASK_ID: Int
        ): String{
            return "home_screen/task_detail/$TASK_ID/complete"
        }

    }


}