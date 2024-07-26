package com.example.kotlindevcourse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

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

        /* Username */
        TextField(
            value = "Username",
            onValueChange = {},
            colors = TextFieldDefaults.colors(Color(0xff00A19B)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 10.dp
                )

        );

        /* Password */
        TextField(
            value = "Password",
            onValueChange = {
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    bottom = 10.dp
                )
        );

        /* Button */
        Button(
            onClick={
                navController.navigate(route = Screen.Home.route)
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