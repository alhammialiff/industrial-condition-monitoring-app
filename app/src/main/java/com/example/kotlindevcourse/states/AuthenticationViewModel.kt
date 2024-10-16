package com.example.kotlindevcourse.states

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.Snapshot.Companion.withMutableSnapshot
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.saveable
import com.example.kotlindevcourse.AuthenticatingUser

class AuthenticationViewModel(savedStateHandle: SavedStateHandle): ViewModel() {

    private val _isAuthenticated: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private val isAuthenticated: Boolean? = _isAuthenticated.value

    /*private val _authenticatingUser: MutableLiveData<AuthenticatingUser> = MutableLiveData<AuthenticatingUser>()
    private val authenticatingUser: AuthenticatingUser? = _authenticatingUser.value*/

    /* HOW TO GET LIVE DATA UPDATES (Eg. Capturing textfield input)?
    * Using MutableLiveData will not update the data real-time
    * because the thread that handles the observable update is not ready.
    * Hence null is always returned.
    *
    * To address this so that we can get data updates real-time,
    * savedStateHandle.saveable is used instead
    * */
    private var _authenticatingUser by savedStateHandle.saveable {
        mutableStateOf<AuthenticatingUser>(
            value = AuthenticatingUser(username = "", password = "")
        )
    }

    fun sendAuthRequest(authenticatingUser: AuthenticatingUser): Unit{

        /* POST Auth API to Backend should be here on prod */
//        _authenticatingUser.value = authenticatingUser

        /* ... But we are simulating authentication for now ... */



        /*if(){

            authSuccess()

        }else{

            authFailure()

        }*/

    }

    /* Capture Username Input */
    fun captureUsernameInput(usernameInput: String){

        /* This is how saveable mutated state value is updated */
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

        return isAuthenticated!!

    }

    fun authFailure(): Boolean{

        return !isAuthenticated!!

    }

}

