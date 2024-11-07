package com.example.kotlindevcourse

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType.Companion.Em
import androidx.compose.ui.unit.TextUnitType.Companion.Sp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kotlindevcourse.states.UserViewModel

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(
    navController: NavHostController,
    fromHomeNavArgs_Username: String
) {

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
                            contentDescription = "Back Button",
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
    ) {

            innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {


            NotificationScreenContainer(
                username = fromHomeNavArgs_Username,
                modifier = Modifier
            )


        }

    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NotificationScreenContainer(
    username: String,
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel = viewModel()
) {

    Column(
        modifier = modifier.padding(20.dp)
    ){

        /* Dummy notifications - to move it up into a separate module that receives parsed data from HTTP Response */
        val notifications: List<Pair<String,String>> = listOf(
            Pair("Info", "Lube Cup Replenishment @ Area A-1"),
            Pair("Info", "Lube Cup Replenishment @ Area A-2"),
            Pair("Warning", "Maintenance in Progress @ Area B-4"),
            Pair("Info", "Lube Cup Replenishment @ Area B-3"),
        )
        FlowRow(
            horizontalArrangement = Arrangement.Start,
            modifier = modifier.padding()
        ){

            /* Iterate notifications into rows */
            notifications.forEach { notification ->

                    if(notification.first == "Info"){

                        Icon(
                            painterResource(id = R.drawable.info_24dp),
                            contentDescription = "Info",
                            modifier = Modifier.padding(10.dp)
                        )

                    }else{

                        Icon(
                            painterResource(id = R.drawable.warning_24dp),
                            contentDescription = "Warning",
                            modifier = Modifier.padding(10.dp)
                        )

                    }

                    Text(
                        text = "${notification.second}",
                        fontSize = TextUnit(20F, Sp),
                        modifier = Modifier.padding(10.dp)
                    )

                    HorizontalDivider()

            }
        }





    }

}

@Composable
@Preview(showBackground = true)
fun NotificationScreenPreview(
    modifier: Modifier = Modifier
){

    NotificationScreen(
        navController = rememberNavController(),
        fromHomeNavArgs_Username = "")

}