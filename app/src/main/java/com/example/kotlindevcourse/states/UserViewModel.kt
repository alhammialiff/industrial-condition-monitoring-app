package com.example.kotlindevcourse.states

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlindevcourse.Screen
import com.example.kotlindevcourse.User

class UserViewModel: ViewModel() {

    /* [DUMMY USER DATA]
    *  Data is prepped for consumption over here via
    *  mutableListOf.
    *
    *  Data takes the form of TaskList class
    *  [!!] This list should be in DB in prod,
    *       and only authenticated user is served by the Backend
    *  */
    var userList = mutableListOf(
        User(
            name = "Stephan Bodzin",
            department = "Electrical",
            designation = "Electrician",
            actionItems = "[TODO]",
            latestActivity = Screen.Home.route,
            lastLoggedIn = "[TODO]",
            lastLoggedOut = "[TODO]"
        ),
        User(
            name = "Tadao Ando",
            department = "Facilities Management",
            designation = "Field Operator",
            actionItems = "[TODO]",
            latestActivity = Screen.Home.route,
            lastLoggedIn = "[TODO]",
            lastLoggedOut = "[TODO]"
        ),
        User(
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

    private val currentUser: User? = _currentUser.value

    fun getCurrentUser(): User{

        return currentUser!!

    }

    fun addNewUser(newUser: User){

        userList.add(newUser)
        Log.d("[Add New User", userList.toString())

    }

    fun setCurrentUser(currentUser: User): Unit{

        _currentUser.value = currentUser

    }



}