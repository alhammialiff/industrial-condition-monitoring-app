
package com.example.kotlindevcourse

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kotlindevcourse.states.TasksViewModel

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailCompleteScreen(
    navController: NavHostController,
    TASK_ID: String? = "null",
    taskViewModel: TasksViewModel = viewModel(),
    userDataStoreManager: UserDataStoreManager = UserDataStoreManager(LocalContext.current)
){

    Log.d("Task Details Complete - TASK_ID", TASK_ID.toString())

    /* Extract specific task by TASK_ID from Task View Model
    *   TASK_ID is the selected (clicked) Task Card's array index
    *
    * */
    /*val specificTask: FieldTask = taskViewModel.getTaskByCurrentIndex(TASK_ID!!)*/

    /*Log.d("specificTask", specificTask.toString())*/

    /* [26/11/2024 DATA STORE RETRIEVAL WIP]  */
    val userDataFlow = remember {
        userDataStoreManager.getFromDataStore()
            .asLiveData()
            .distinctUntilChanged()
    }

    val user2 by userDataFlow.observeAsState()

    /* Extract specific task by TASK_ID from Task View Model
    *   TASK_ID is the selected (clicked) Task Card's array index
    *
    * */
    /*val specificTask: FieldTask = taskViewModel.getTaskByCurrentIndex(TASK_ID!!)*/
    val specificTask: FieldTask2? = user2?.actionItems?.outstanding?.find { it.taskID == TASK_ID }

    /* [Debug] Current Nav Route */
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    Log.d("user2", user2.toString())
    Log.d("CURRENT ROUTE", currentRoute.toString())

    /* Set page structure using Scaffold */
    Scaffold(

        /* containerColor parameter */
        containerColor = Color.White,

        /* topBar parameter */
        topBar = {
            TopAppBar(

                colors = topAppBarColors(
                    containerColor = Color(0xff00A19B),
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    if (specificTask != null) {
                        Text(
                            text= specificTask.action,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color( 0xffffffff)
                        )
                    }
                }

            )
        },

        /* bottomBar parameter */
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xff00A19B),
                contentColor = MaterialTheme.colorScheme.primary
            ){

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){

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
                    ){

                        Icon(
                            painterResource(backArrowIconImage),
                            contentDescription = "Back Button",
                            modifier = Modifier.size(45.dp)
                        )
                    }

                    /*Menu Arrow*/
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
    ){
            innerPadding ->

        /* [Log] Check retrieved TASK_ID from Home Screen */

        /* This column houses the container that encapsulates the layout */
        Column(
            modifier = Modifier.padding(innerPadding)
        ){

            TaskDetailCompleteScreenPageContainer(
                navController =  navController,
                TASK_ID = TASK_ID,
                task = specificTask,
                user2 = user2,
                modifier = Modifier
            )

        }
    }



}

/* This is the container that encapsulates all the components residing in this screen */
@Composable
fun TaskDetailCompleteScreenPageContainer(
    navController: NavHostController,
    TASK_ID: String?,
    task: FieldTask2?,
    user2: User2?,
    modifier: Modifier = Modifier,
    tasksViewModel: TasksViewModel = viewModel()
){

    /* Extract Task based on TASK_ID (i.e index of task selected by user) */
    /*val taskMasterList = tasksViewModel.getInitTaskList().getAllTasks()
    var selectedTask = taskMasterList[TASK_ID]
    Log.d("[Tut Complete] taskMasterList", taskMasterList.toString())

    Log.d("[Tut Complete] SELECTEDTASK", selectedTask.toString())*/

    /*
    *   Extract Task Title (action) and display it in the congratulatory message
    * */
    Column(
        modifier = modifier
            .background(Color(0xff00A19B))
            .fillMaxWidth()
            .fillMaxHeight()

    ){

        /* Column for Congratulatory Message
        *
        *  These nested columns are defined so that
        *  congratulatory message is somewhat centered
        *  and button can be pushed to the bottom of the screen
        *  via the float value in fillMaxHeight prop
        * */
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .padding(20.dp)

        ) {

            /* Thumbs up icon */
            Image(
                painterResource(R.drawable.thumbs_up),
                contentDescription = "Completed",
                modifier = modifier
                    .size(100.dp)
                    .align(
                        alignment = Alignment.CenterHorizontally
                    )
                    .padding(
                        bottom=10.dp
                    )
            )

            /* Task Name */
            if (task != null) {
                Text(
                    text = task.action,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    fontWeight = FontWeight(800),
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .align(
                            alignment = Alignment.CenterHorizontally
                        )
                )
            }

            /* Congratulatory Message */
            Text(
                text = "Task Completed!",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight(800),
                textAlign = TextAlign.Center,
                modifier = modifier
                    .align(
                        alignment = Alignment.CenterHorizontally
                    )


            )

        }

        /* Column for Button */
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = modifier
                .padding(20.dp)
                .align(
                    alignment = Alignment.CenterHorizontally
                )
                .fillMaxHeight()
        ){

            user2?.let { Log.d("user2", it.username) }

            Button(
                onClick = {
                    /*navController.navigate(
                        route = Screen.Home.route
                    )*/

                    if (user2 != null) {
                        navController.navigate(
                            route = Screen.Home.route + "/{username}".replace(
                                oldValue = "{username}",
                                newValue = "stephanbodzin"
                            )
                        )
                    }
                },
                modifier = modifier
                    .fillMaxWidth()

            ){

                Text(
                    text = "Back to Dashboard",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = modifier.padding(3.dp)
                )

            }
        }

    }










}

@Composable
@PreviewScreenSizes()
fun TaskDetailCompleteScreenPreview(modifier: Modifier = Modifier){

    TaskDetailCompleteScreen(
        navController = rememberNavController()
    )

}
