package com.example.kotlindevcourse

import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.kotlindevcourse.states.AuthenticationViewModel

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
    authenticationViewModel: AuthenticationViewModel = viewModel()
) {

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
                if(authenticationViewModel.getAuthResult()){

                    Log.d("[Auth Success]","Redirecting to Home")

                    /* [To remember] We can pass string arg in nav route
                    *                by performing concat and replace like below
                    * */
                    navController.navigate(
                        route = Screen.Home.route + "/{username}".replace(
                            oldValue = "{username}",
                            newValue = usernameInput
                        )
                    )
                }else{

                    Log.d("[Auth Success]","Redirecting to Login")
                    navController.navigate(route = Screen.Login.route)

                }

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

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(modifier: Modifier = Modifier) {

    LoginContainer(
        navController = rememberNavController()
    )

}