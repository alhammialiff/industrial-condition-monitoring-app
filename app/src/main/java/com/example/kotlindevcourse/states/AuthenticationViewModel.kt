package com.example.kotlindevcourse.states

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.Snapshot.Companion.withMutableSnapshot
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.saveable
import com.example.kotlindevcourse.AuthenticatingUser
import com.example.kotlindevcourse.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/*class AuthenticationViewModel(savedStateHandle: SavedStateHandle): ViewModel() {

*//*    private val _isAuthenticated: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private val isAuthenticated: Boolean? = _isAuthenticated.value
    *//*
    private var _isAuthenticated by savedStateHandle.saveable {
        mutableStateOf<Boolean>(
            value = false
        )
    }

    *//*private val _authenticatingUser: MutableLiveData<AuthenticatingUser> = MutableLiveData<AuthenticatingUser>()
    private val authenticatingUser: AuthenticatingUser? = _authenticatingUser.value*//*

    *//* HOW TO GET LIVE DATA UPDATES (Eg. Capturing textfield input)?
    * Using MutableLiveData will not update the data real-time
    * because the thread that handles the observable update is not ready.
    * Hence null is always returned.
    *
    * To address this so that we can get data updates real-time,
    * savedStateHandle.saveable is used instead
    * *//*
    private var _authenticatingUser by savedStateHandle.saveable {

        mutableStateOf<AuthenticatingUser>(
            value = AuthenticatingUser(username = "", password = "")
        )

    }

    var _user by savedStateHandle.saveable {

        mutableStateOf<User>(
            value = User(
                name ="",
                username = "",
                department =  "",
                designation = "",
                actionItems = "",
                latestActivity = "",
                lastLoggedIn = "",
                lastLoggedOut = ""
            )
        )

    }

    *//* [Currently simulated]
    *   Retrieve Auth Result based on auth check
    * *//*
    fun getAuthResult(): Boolean{

        *//* POST Auth API to Backend should be here on prod *//*

        *//* ... But we are simulating authentication for now ... *//*

        *//* [Auth Simulation] Retrieve user list *//*
        withMutableSnapshot {
            _user = UserViewModel().getCurrentUserByName(_authenticatingUser.username)!!
        }

        Log.d("[User Found]", _user.toString())


        *//* [Auth Simulation] If username is the same, auth success *//*
        if(_user !== null){

            return authSuccess()

        }else{

            return authFailure()

        }

    }

    *//* Capture Username Input *//*
    fun captureUsernameInput(usernameInput: String){

        *//* This is how saveable mutated state value is updated *//*
        withMutableSnapshot{
            _authenticatingUser.username = usernameInput
        }

        Log.d("[Change Username Input]", usernameInput)
        Log.d("[Username Input]", _authenticatingUser.username)

    }

    fun capturePasswordInput(passwordInput: String){

        withMutableSnapshot {
            _authenticatingUser.password = passwordInput
        }

        Log.d("[Change Password Input]", passwordInput)
        Log.d("[Password Input]", _authenticatingUser.password)

    }

    fun authSuccess(): Boolean{

        withMutableSnapshot {
            _isAuthenticated = true
        }

        return _isAuthenticated

    }

    fun authFailure(): Boolean{

        withMutableSnapshot {
            _isAuthenticated = false
        }

        return _isAuthenticated

    }

    fun getUser(): User{

        val currentUser = _user

        Log.d("[getUser()]", currentUser.toString())

        *//* [WIP] Should return authenticated user *//*
        return _user

    }

}*/


class AuthenticationViewModel : ViewModel() {
    private var _authenticatingUser = mutableStateOf(AuthenticatingUser(username = "", password = ""))

    fun getAuthResult(): Boolean {

        val username = _authenticatingUser.value.username
        val password = _authenticatingUser.value.password


        /* [SIM ONLY] - to apply proper authentication during backend integration */
        if(username !== ""){

            return true

        }else{

            return false

        }

    }

    fun captureUsernameInput(usernameInput: String) {
        _authenticatingUser.value = _authenticatingUser.value.copy(username = usernameInput)
    }

    fun capturePasswordInput(passwordInput: String) {
        _authenticatingUser.value = _authenticatingUser.value.copy(password = passwordInput)
    }


}
