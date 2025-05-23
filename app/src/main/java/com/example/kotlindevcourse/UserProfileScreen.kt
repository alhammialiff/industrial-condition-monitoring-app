package com.example.kotlindevcourse

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kotlindevcourse.states.UserViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun UserProfileScreen(
    navController: NavHostController,
    fromHomeNavArgs_Username: String) {

    var backStackEntry = navController.currentBackStackEntry
    Log.d("[Curr BSEntry]", backStackEntry.toString())



    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(

                colors = topAppBarColors(
                    containerColor = Color(0xff00A19B),
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(
                        text = "Field Work Ecosystem",
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xffffffff)
                    )
                }

            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xff00A19B),
                contentColor = MaterialTheme.colorScheme.primary
            ) {

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    // Instantiate Icon
                    val backArrowIconImage = R.drawable.arrow_back
                    val forwardArrowIconImage = R.drawable.arrow_forward
                    val menuIconImage = R.drawable.menu

                    /* Back Arrow */
                    Button(
                        onClick = { navController.popBackStack() },
                        colors = ButtonColors(
                            containerColor = Color(0xff00A19B),
                            disabledContainerColor = Color(0xff00A19B),
                            contentColor = Color(0xffffffff),
                            disabledContentColor = Color(0xffffffff)
                        )
                    ) {

                        Icon(
                            painterResource(backArrowIconImage),
                            contentDescription = "Quick Access Button Prototype",
                            modifier = Modifier.size(45.dp)
                        )

                    }

                    /* Menu Arrow */
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonColors(
                            containerColor = Color(0xff00A19B),
                            disabledContainerColor = Color(0xff00A19B),
                            contentColor = Color(0xffffffff),
                            disabledContentColor = Color(0xffffffff)
                        )
                    ) {

                        Icon(
                            painterResource(menuIconImage),
                            contentDescription = "Quick Access Button Prototype",
                            modifier = Modifier.size(45.dp)
                        )

                    }

                    /* Forward Arrow*/
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonColors(
                            containerColor = Color(0xff00A19B),
                            disabledContainerColor = Color(0xff00A19B),
                            contentColor = Color(0xffffffff),
                            disabledContentColor = Color(0xffffffff)
                        )
                    ) {

                        Icon(
                            painterResource(forwardArrowIconImage),
                            contentDescription = "Quick Access Button Prototype",
                            modifier = Modifier.size(45.dp)
                        )
                    }
                }

            }
        }

    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding)
        ) {


            UserProfileContainer(
                username = fromHomeNavArgs_Username,
                modifier = Modifier)


        }

    }

}

@Composable
fun UserProfileContainer(
    username: String,
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel = viewModel()
){

    val user: UserToDelete? = userViewModel.getCurrentUserByName(username)


    /* Welcome Header */
    user?.let {

        Text(
            text = "Welcome ${user.name}",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = modifier.padding(20.dp)
        )

        /* User Details */
        Column (

        ){
            /* Username Row */
            Row(modifier = Modifier.padding(
                top = 10.dp,
                start = 20.dp,
                end = 20.dp,
                bottom = 0.dp
            )){

                /* Instantiate Icon */
                val usernameFieldIconImage = R.drawable.user_24

                /* Define Icon Class with Icon Image */
                Icon(
                    painterResource(usernameFieldIconImage),
                    contentDescription = "Quick Access Button Prototype",
                    modifier = modifier.size(45.dp)
                )

                Text(
                    text = user.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    modifier = modifier.padding(20.dp)
                )

            }

            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)

            /* Team Row */
            Row(modifier = Modifier.padding(
                top = 10.dp,
                start = 20.dp,
                end = 20.dp,
                bottom = 0.dp
            )){

                /* Instantiate Icon */
                val usernameFieldIconImage = R.drawable.groups_of_3

                /* Define Icon Class with Icon Image */
                Icon(
                    painterResource(usernameFieldIconImage),
                    contentDescription = "Quick Access Button Prototype",
                    modifier = modifier.size(45.dp)
                )

                Text(
                    text = user.department,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    modifier = modifier.padding(20.dp)
                )



            }

            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)

            /* Designation Row */
            Row(modifier = Modifier.padding(
                top = 10.dp,
                start = 20.dp,
                end = 20.dp,
                bottom = 0.dp
            )){

                /* Instantiate Icon */
                val usernameFieldIconImage = R.drawable.work

                /* Define Icon Class with Icon Image */
                Icon(
                    painterResource(usernameFieldIconImage),
                    contentDescription = "Quick Access Button Prototype",
                    modifier = modifier.size(45.dp)
                )

                Text(
                    text = user.designation,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    modifier = modifier.padding(20.dp)
                )



            }

            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)

        }

    }










}

@Composable
@Preview(showBackground = true)
fun UserProfileScreenPreview(modifier: Modifier = Modifier){

    UserProfileScreen(
        navController = rememberNavController(),
        fromHomeNavArgs_Username="username_here"
    )

}
