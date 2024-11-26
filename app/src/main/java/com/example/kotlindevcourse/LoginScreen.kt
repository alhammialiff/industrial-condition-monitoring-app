package com.example.kotlindevcourse

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.kotlindevcourse.states.AuthenticationViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.kotlindevcourse.states.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
){

    LoginContainer(
        navController = navController,
        modifier = Modifier

    )

}

@Composable
fun LoginContainer(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    authenticationViewModel: AuthenticationViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel()
) {

    val scope = rememberCoroutineScope()
    val ctx = LocalContext.current
    val loginResponse = remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .padding(20.dp)

    ){

        /* Text */
        Text(
            text="Login",
            style = MaterialTheme.typography.displayLarge,

        )
        Text(
            text="Sign in to continue",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xff00A19B)
            )

    }

    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp)

    ){

        var usernameInput by remember { mutableStateOf("") }
        var passwordInput by remember { mutableStateOf("") }
        var passwordVisibility by remember { mutableStateOf(false)}

        /* Icon changes with passwordVisibility flag */
        val icon = if(passwordVisibility)
            painterResource(id = R.drawable.visibility)
        else
            painterResource(id = R.drawable.visibility_off)

        /* Username Field */
        TextField(
            value = usernameInput,
            singleLine = true,
            label = {
                Text("Username")
            },
            onValueChange = {
                usernameInput = it
                authenticationViewModel.captureUsernameInput(usernameInput)
            },
            colors = TextFieldDefaults.colors(Color(0xff00A19B)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 10.dp
                )

        );

        /* Password Field */
        TextField(
            value = passwordInput,
            label = {
                Text("Password")
            },
            singleLine = true,
            onValueChange = {
                passwordInput = it
                authenticationViewModel.capturePasswordInput(passwordInput)
            },

            /* When this is set, textfield no longer has underscore (aesthetics) */
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    bottom = 10.dp
                ),

            /* Props contains lambda function to set pwd visible icon change when clicked */
            trailingIcon = {

                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }){

                    Icon(
                        painter = icon,
                        contentDescription="Hide Password"
                    )

                }
            },

            /* Prop handles pwd visibility when passwordVisibility flag toggles */
            visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()

        );

        /* Button */
        Button(
            onClick={

                val authenticatingUser = AuthenticatingUser(usernameInput, passwordInput)

                /* Navigation based on auth result
                *
                *  [06/11/2024] HOW DO WE PASS USERNAME DATA ON SUCCESSFUL AUTH?
                *   (1) If auth successful, pass username string as args in nav route
                *   (2) Else, redirect back to Login Screen
                *   Note;
                *       After trying ways to process authenticated user data in ViewModel
                *       so that it may be retrieved in Home Screen (failed), passing authed
                *       user data is rethought and rewired differently. Currently, a working
                *       solution is to pass username string data by appending it onto route
                *       string.
                * */

                /* [UNCOMMENT TO ENABLE DUMMY AUTH] */
                /*if(authenticationViewModel.getAuthResult()){

                    Log.d("[Auth Success]","Redirecting to Home")

                    *//* [To remember] We can pass string arg in nav route
                    *                by performing concat and replace like below
                    * *//*
                    navController.navigate(
                        route = Screen.Home.route + "/{username}".replace(
                            oldValue = "{username}",
                            newValue = usernameInput
                        )
                    )
                }else{

                    Log.d("[Auth Success]","Redirecting to Login")
                    navController.navigate(route = Screen.Login.route)

                }*/

                /* [UNCOMMENT TO ENABLE ACTUAL BACKEND LOGIN CALL]
                *   Begin invoking async operation of HTTP POST /login
                *
                * */
                performLogin(
                    ctx,
                    scope,
                    authenticatingUser,
                    loginResponse,
                    navController,
                    usernameInput,
                    userViewModel)

            },
            modifier = modifier
                .fillMaxWidth()
        ){

            Text(
                text="Login"
            )


        }


    }

}


/* Private module to launch coroutine and perform async operation
*   Eg. Perform Login
*   Source: https://medium.com/@kathankraithatha/how-to-use-api-in-jetpack-compose-10d11b8f166f
* */
private fun performLogin(
    context: Context,
    scope: CoroutineScope,
    authenticatingUser: AuthenticatingUser,
    loginResponse: MutableState<String>,
    navController: NavController,
    usernameInput: String,
    userViewModel: UserViewModel,
    dataStoreManager: UserDataStoreManager = UserDataStoreManager(context)
) {

    Log.d("[Login]", "Sending creds over /login: ${authenticatingUser}",)

    scope.launch {

        try {

            val call: Call<LoginResponse?>? = RetrofitInstance.loginService.login(authenticatingUser)

            Log.d("[Sending POST Call]", call.toString())

            call!!.enqueue(object : Callback<LoginResponse?> {

                var authSuccess = false;

                /*
                *    onResponse will only trigger if HTTP Code returns 200-299
                *  */
                override fun onResponse(call: Call<LoginResponse?>?, response: Response<LoginResponse?>) {

                    /* Display Toast */
                    Toast.makeText(context, "Login sent", Toast.LENGTH_SHORT).show()

                    val model: LoginResponse? = response.body()

                    /* Generally Auth Success is here */
                    if(model !== null) {

                        /* HTTP 200 - Auth Success */
                        if(model.responseCode == 200){

                            val responseMessage = "Response Code: " + response.code() + "\n" + "Username:" + model!!
                            val retrievedUser = model.data

                            /* [WIP] Datastore Dev in progress */
                            scope.launch {

                                if (retrievedUser != null) {

                                    Log.d("[Login - saving to datastore]", retrievedUser.toString())

                                    /* [Commented First] Because of conflicting TaskID datatype between model and DB*/
                                    /*val userSerialised = Json.encodeToString<User>(retrievedUser)*/

                                    val userSerialised = Json.encodeToString<User2>(retrievedUser)

                                    dataStoreManager.saveToDataStore(userSerialised)

                                }

                            }

                            Log.d("[Response - OK]", model.toString())

                            loginResponse.value = responseMessage

                            /* Navigate to home */
                            if(retrievedUser?.username != null){

                                /* Set authenticated user into this session user state */
                                userViewModel.setThisUser(retrievedUser)

                                navController.navigate(
                                    route = Screen.Home.route + "/{username}".replace(
                                        oldValue = "{username}",
                                        newValue = retrievedUser.username
                                    )
                                )

                            /* This is to handle a situation where HTTP Resp is 200 but data is returned empty */
                            }else{

                                /* If code hits here, there is a bug in the condition above */
                                navController.navigate(route = Screen.Login.route)

                            }


                        /* HTTP xxx - Anything other than 200 is considered auth failure */
                        }else{

                            navController.navigate(route = Screen.Login.route)

                        }


                    /* Otherwise auth failure is generally here (need to test) */
                    } else {

                        Log.d("[Auth Failed]","Redirecting back to Login")

                        navController.navigate(route = Screen.Login.route)

                    }




                }

                /* onFailure call back is triggered on HTTP Error Code (400..,500)*/
                override fun onFailure(call: Call<LoginResponse?>?, t: Throwable){

                    loginResponse.value = "HTTP POST Failure: " + t.message
                    Log.d("[Login Response - FAIL]", t.message.toString())

                    navController.navigate(route = Screen.Login.route)

                }

            })

        // Handle the response
        } catch (e: Exception) {

            // Handle the error

        }

    }

}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(modifier: Modifier = Modifier) {

    LoginContainer(
        navController = rememberNavController()
    )

}