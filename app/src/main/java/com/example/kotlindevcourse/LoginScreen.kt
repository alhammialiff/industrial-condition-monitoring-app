package com.example.kotlindevcourse

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginScreen(navController: NavHostController){

    LoginContainer(navController = navController)

}

@Composable
fun LoginContainer(
    navController: NavHostController,
    modifier: Modifier = Modifier) {

    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()

    ){

        /* Text */
        Text(
            text="Login",
            style = MaterialTheme.typography.displayLarge
        )

        /* Username */
        TextField(
            value = "Username",
            onValueChange = {}
        );

        /* Password */
        TextField(value = "Password", onValueChange = {


        });

        /* Button */
        Button(
            onClick={
                navController.navigate(route = Screen.Home.route)
            },
        ){

            Text(
                text="Login"
            )


        }


    }

}

@Preview()
@Composable
fun LoginScreenPreview(modifier: Modifier = Modifier) {

    LoginContainer(
        navController = rememberNavController()
    )

}