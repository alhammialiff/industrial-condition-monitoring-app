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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun TaskDetailStartScreen(
    navController: NavHostController,
    taskID: String?,
    taskViewModel: TasksViewModel = viewModel(),
    userDataStoreManager: UserDataStoreManager = UserDataStoreManager(LocalContext.current),
    ){

    Log.d("Task Details - taskID", taskID.toString())

    /* [DATA RETRIEVAL FROM PERSISTENT DATA STORE] */
    val userDataFlow = remember {
        userDataStoreManager.getFromDataStore()
            .asLiveData()
            .distinctUntilChanged()
    }

    val userData by userDataFlow.observeAsState()

    /* Extract specific task by TASK_ID from Task View Model
    *   TASK_ID is the selected (clicked) Task Card's array index
    *
    * */
    /*val specificTask: FieldTask = taskViewModel.getTaskByCurrentIndex(TASK_ID!!)*/
    val task: Task? = userData?.allTasks?.outstanding?.find {
        it.taskID == taskID.toString()
    }

    Log.d("specificTask", task.toString())

    /* [Debug] Current Nav Route */
    val currentRoute = navController.currentBackStackEntry?.destination?.route
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
                    
                    /* Non-nullable check of action item before rendering Text composable */
                    if (task != null) {

                        Text(
                            text= task.action,
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

            if (taskID != null) {
                TaskDetailStartScreenPageContainer(
                    navController =  navController,
                    task = task,
                    taskID = taskID,
                    modifier = Modifier
                )
            }

        }
    }



}

/* This is the container that encapsulates all the components residing in this screen */
@Composable
fun TaskDetailStartScreenPageContainer(
    navController: NavHostController,
    task: Task?,
    taskID: String,
    modifier: Modifier = Modifier,
    tasksViewModel: TasksViewModel = viewModel()
){

    /* [LOCAL KOTLIN ENV DUMMY DATA]
    *   Extract Task based on TASK_ID (i.e index of task selected by user)
    * */
    /*
        val taskMasterList = tasksViewModel.getInitTaskList().getAllTasks()
        var selectedTask = taskMasterList[TASK_ID]
        Log.d("[Tut Start]taskMasterList", taskMasterList.toString())
    */

    val lubeCupImage = painterResource(id = R.drawable.lube_oil_cup)
    val areaMapImage = painterResource(id = R.drawable.area_map)

    /* Retrieve Task based on saved current index that was set when user clicked
    *  the Task Card in Home Screen
    * */
    Box(
        modifier = modifier
            .background(Color(0xff00A19B))
    ) {

        /* Components on this page is structured in this column */
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
//            .fillMaxWidth()
//            .fillMaxHeight(0.5f)
                .padding(20.dp)
        ) {

            /* TODO: Lube Cup Image */
            Surface(
                shadowElevation = 8.dp,
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
            ) {
                if (task != null) {
                    Image(
                        painter = lubeCupImage,
                        contentDescription = task.action,
                        contentScale = ContentScale.FillWidth,
                        modifier = modifier
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(
                                    topStart = 30.dp,
                                    topEnd = 30.dp,
                                    bottomStart = 30.dp,
                                    bottomEnd = 30.dp
                                )
                            )
                    )
                }
            }

        }
    }

    /* TODO: STOP HERE 07/10/2024
    *   Center text horizontally */
    Column(
        modifier = modifier
            .padding(20.dp)

    ){
        if (task != null) {
            Text(
                text = task.action,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold
            )
        }

        /* Training Overview Card */
        Column(
            modifier = modifier
                .padding(top = 20.dp)
                .background(Color(0xff00A19B), RoundedCornerShape(12.dp))
                .padding(20.dp)

        ){

            Row(
                modifier = modifier
                    .padding(bottom = 10.dp)
            ){


                Image(
                    painterResource(R.drawable.step_count_foreground),
                    contentDescription = "No. of Steps",
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 5.dp)
                )

                Text(
                    text = "3 steps",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = modifier.weight(1f)
                )

                Image(
                    painterResource(R.drawable.time),
                    contentDescription = "Approx. Task Duration",
                    modifier = Modifier.size(20.dp)
                )

                Text(
                    text = "20 mins",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = modifier.weight(1f)
                )

            }

            Row(
                modifier = modifier
            ){

                Image(
                    painterResource(R.drawable.pax_foreground),
                    contentDescription = "Approx. Task Duration",
                    modifier = Modifier.size(20.dp)
                )

                Text(
                    text = "2 pax",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = modifier.weight(1f)
                )

                Image(
                    painterResource(R.drawable.difficulty_foreground),
                    contentDescription = "Approx. Task Duration",
                    modifier = Modifier.size(20.dp)
                )

                Text(
                    text = "Easy",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = modifier.weight(1f)
                )

            }

        }

        /* TODO: Button Row */
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = modifier.weight(1f),

        ){

            /* Back Button */
            Surface(
                shadowElevation = 4.dp,
                shape = RoundedCornerShape(20.dp),
                color = Color(0xffd4d4d4),
                modifier = Modifier.padding(3.dp)
            ){


                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonColors(
                        containerColor = Color(0xffd4d4d4),
                        contentColor = Color(0xffd4d4d4),
                        disabledContainerColor = Color(0xffd4d4d4),
                        disabledContentColor = Color(0xffd4d4d4)
                    ),
                    modifier = modifier.fillMaxWidth(0.5f)

                ) {

                    Text(
                        text = "Back",
                        color = Color.Black,
                    )

                }

            }

            /* Start Button */
            Surface(
                shadowElevation = 4.dp,
                shape = RoundedCornerShape(20.dp),
                color = Color(0xff00736f),
                modifier = Modifier.padding(3.dp)
            ){

                Button(

                    onClick = {
                        navController.navigate(
                            route = Screen.TaskDetailStep.passTaskIDandStepID(
                                TASK_ID = taskID,
                                STEP_ID = 0
                            )
                        )
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonColors(
                        containerColor = Color(0xff00736f),
                        contentColor = Color(0xff00736f),
                        disabledContainerColor = Color(0xff00736f),
                        disabledContentColor = Color(0xff00736f)
                    ),
                    modifier = modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "Start",
                        color = Color.White,
                    )

                }

            }


        }



    }






}

@Composable
@PreviewScreenSizes()
fun TaskDetailStartScreenPreview(modifier: Modifier = Modifier){

    TaskDetailStartScreen(
        navController = rememberNavController(),
        taskID = "null"
    )

}
