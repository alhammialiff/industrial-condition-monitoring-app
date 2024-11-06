package com.example.kotlindevcourse.states

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlindevcourse.Screen
import com.example.kotlindevcourse.User

open class UserViewModel: ViewModel() {

    /* [DUMMY USER DATA]
    *  Data is prepped for consumption over here via
    *  mutableListOf.
    *
    *  Data takes the form of TaskList class
    *  [!!] This list should be in DB in prod,
    *       and only authenticated user is served by the Backend
    *  */
    private var userList = mutableListOf(
        User(
            username = "stephanbodzin",
            name = "Stephan Bodzin",
            department = "Electrical",
            designation = "Electrician",
            actionItems = "[TODO]",
            latestActivity = Screen.Home.route,
            lastLoggedIn = "[TODO]",
            lastLoggedOut = "[TODO]"
        ),
        User(
            username = "tadaoando",
            name = "Tadao Ando",
            department = "Facilities Management",
            designation = "Field Operator",
            actionItems = "[TODO]",
            latestActivity = Screen.Home.route,
            lastLoggedIn = "[TODO]",
            lastLoggedOut = "[TODO]"
        ),
        User(
            username = "johnroti",
            name = "John Roti",
            department = "Process Operations",
            designation = "Process Technician",
            actionItems = "[TODO]",
            latestActivity = Screen.Home.route,
            lastLoggedIn = "[TODO]",
            lastLoggedOut = "[TODO]"
        ),
    )

    private val _currentUser: MutableLiveData<User> = MutableLiveData<User>()

    fun getCurrentUserByName(requestingUsername: String): User? {

        /* [Auth Simulation] Iterate user list */
        for (user in userList) {

            Log.d("[Auth Sim]", user.toString())

            if (user.username.contains(requestingUsername)) {

                Log.d("[Auth Sim]", "Hit")

                return user;

            }

        }

        return null

    }

    fun addNewUser(newUser: User) {

        userList.add(newUser)
        Log.d("[Add New User", userList.toString())

    }

    fun setCurrentUser(currentUser: User): Unit {

        _currentUser.value = currentUser

    }

}