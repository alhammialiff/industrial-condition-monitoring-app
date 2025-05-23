package com.example.kotlindevcourse

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kotlindevcourse.states.TasksViewModel
import com.example.kotlindevcourse.states.UserViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

//import com.example.kotlindevcourse.navigation.HomeScreenNav

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onUserProfileButtonClicked: () -> Unit,
    onNotificationButtonClicked: () -> Unit,
    onTaskCardClicked: NavHostController,
    fromLoginNavArgs_Username: String = ""
) {

    /* [Debug] Backstack Entry Record */
    var backStackEntry = onTaskCardClicked.currentBackStackEntry
    Log.d("[Curr BSEntry]", backStackEntry.toString())
    Log.d("[username arg from BSEntry]", fromLoginNavArgs_Username)

    Scaffold(
        containerColor = Color.White,
        topBar = {

            TopAppBar(

                colors = topAppBarColors(
                    containerColor = Color(0xff00A19B),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title={},

                /* This modifier is to shorten the default height of TopAppBar*/
                modifier=Modifier.height(5.dp)

            )

        },
        bottomBar = {

            /* From-scratch Bottom Bar */
            /*BottomBar(navController = navController)*/

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

                    /*Back Arrow */
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
                            painterResource(backArrowIconImage),
                            contentDescription = "Quick Access Button Prototype",
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
    ) {

        innerPadding ->

        /*https://medium.com/@mathroda/nested-navigation-graph-in-jetpack-compose-with-bottom-navigation-d983c2d4119f*/
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {

            val configuration = LocalConfiguration.current
            val screenWidth: Dp = configuration.screenWidthDp.dp
            val screenHeight: Dp = configuration.screenHeightDp.dp

            BodyContent(
                onUserProfileButtonClicked = onUserProfileButtonClicked ,
                onNotificationButtonClicked = onNotificationButtonClicked,
                onTaskCardClicked = onTaskCardClicked,
                width = screenWidth,
                height = screenHeight,
                message = "Body Content",
                from = "Quick Access",
                modifier = Modifier,
                username = fromLoginNavArgs_Username
            )

        }

    }

}

class FieldTasksViewModel : ViewModel() {

    private val _numOfOutstandingTasks: MutableLiveData<Int> = MutableLiveData(6)

    val numOfOutstandingTasks: LiveData<Int> = _numOfOutstandingTasks

    /* [Note] Wire this up with the function that serves updated data
    *         originated from the backend
    *  */
    fun onDataChange(updatedNumOfOutstandingTasks: Int) {

        _numOfOutstandingTasks.value = updatedNumOfOutstandingTasks

    }

}

@Composable
fun OverviewSectionGroup(fieldTaskViewModel: FieldTasksViewModel = viewModel()) {

    /* State subscription vars here */
    val numOfOutstandingTasks: Int by fieldTaskViewModel.numOfOutstandingTasks.observeAsState(
        initial = 0
    )

    /* Instantiate Overview Section with state subscription vars wired up */
    OverviewSection(
        numOfOutstandingTasks = numOfOutstandingTasks
    )

}



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OverviewSection(
    numOfOutstandingTasks: Int,
    modifier: Modifier = Modifier

) {

    FlowRow(
        horizontalArrangement = Arrangement.SpaceEvenly,

        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 30.dp,
                    bottomEnd = 30.dp
                ),
            )
            .background(Color(0xff00A19B))
            /* Layout Guideline */
//            .border(
//                border = BorderStroke(0.dp, Color.LightGray),
//                shape = RectangleShape
//            ),

    ) {

        // Outstanding Tasks Number here
        OverviewNumberCard(
            numOfOutstandingTasks = numOfOutstandingTasks,
        )

    }

}

@Composable
fun OverviewNumberCard(
    numOfOutstandingTasks: Int,
    modifier: Modifier = Modifier.padding(
        0.dp,
        0.dp,
        0.dp,
        20.dp
    )
) {

    Column(
        modifier = Modifier
//            .border(
//                border = BorderStroke(2.dp, Color(0xff4370cf)),
//                shape = RoundedCornerShape(12.dp),
//            )
            .background(Color(0xff00A19B), RoundedCornerShape(12.dp))
            .padding(
                20.dp,
                0.dp,
                20.dp,
                0.dp
            )

    ) {

        /* Define sub composables */
        Text(
            text = "$numOfOutstandingTasks",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 70.sp,
            modifier = modifier,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White
        )

        /* Define sub composables */
        Text(
            text = "tasks",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            style = MaterialTheme.typography.labelLarge,
            color = Color.White,
            modifier = Modifier
                .padding(bottom=20.dp)
        )

    }


    /* Outline event handler that changes state of this composable */


}

@Composable
fun ProfilePictureContainer(
    profilePicture: String = "",
    modifier: Modifier = Modifier
){
    Box(
        modifier= modifier
    ){


        Image(
            painter = painterResource(id = R.drawable.user_24),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(70.dp)
                .clip(CircleShape)
                .border(2.dp, Color(0xffa4e8ef), CircleShape)
        )

    }

}

//Composable Function that can be used by setContent()
@OptIn(ExperimentalCoroutinesApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun BodyContent(
    onUserProfileButtonClicked: () -> Unit,
    onNotificationButtonClicked: () -> Unit,
    onTaskCardClicked: NavHostController,
    width: Dp,
    height: Dp,
    message: String,
    from: String,
    modifier: Modifier = Modifier,
    username: String = "",
    userViewModel: UserViewModel = viewModel(),
    userDataStoreManager: UserDataStoreManager = UserDataStoreManager(LocalContext.current),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {



    /* [PRE-REST DUMMY DATA PROTOTYPE]
    *   Retrieve User Data by using the nav args 'username' passed by Login Screen
    *   username from route param is used to retrieve data based on the username.
    *   The dummy data was stored in UserViewModel.
    * */
    /*  val user = userViewModel.getCurrentUserByName(username)*/

    /* [DATA RETRIEVAL FROM PERSISTENT DATA STORE - FAILED ATTEMPT #1 VAR]
    *  User is defined as a MutableState<User>
    *  This type is needed because user should be mutable.
    *  That is why remember{} is used.
    * */
    /*val user = remember { mutableStateOf(
        User(
            username = "",
            name = "",
            email = "",
            role = "",
            department = "",
            designation = "",
            actionItems = ActionItems(
                outstanding = mutableListOf(
                    FieldTask(
                        taskID = -1,
                        action = "",
                        taskSteps = arrayOf<TaskStep>(
                            TaskStep(
                                stepID = -1,
                                description = "",
                            )
                        ),
                        timestamp = "String",
                        location = "String",
                        priority = "String",
                        taskFor = "",
                        completed = false
                    )
                ),
                mutableListOf(
                    FieldTask(
                        taskID = -1,
                        action = "",
                        taskSteps = arrayOf<TaskStep>(
                            TaskStep(
                                stepID = -1,
                                description = "",
                            )
                        ),
                        timestamp = "String",
                        location = "String",
                        priority = "String",
                        taskFor = "",
                        completed = false
                    )
                ),
                mutableListOf(
                    FieldTask(
                        taskID = -1,
                        action = "",
                        taskSteps = arrayOf<TaskStep>(
                            TaskStep(
                                stepID = -1,
                                description = "",
                            )
                        ),
                        timestamp = "String",
                        location = "String",
                        priority = "String",
                        taskFor = "",
                        completed = false
                    )
                )
            ),
            latestActivity = "",
            lastLoggedOut = "",
            lastLoggedIn = ""
            )
        )
    }*/

    /* [DATA RETRIEVAL FROM PERSISTENT DATA STORE (WORKING SOLUTION)]
    *  Goals: To emit latest data from data store just once
    *
    *  How?:
    *    (1) Wire up Flow with .asLiveData() and .distinctUntilChanged()
    *    (2) 'Subscribe' to data by hooking up .observeAsState()
    *
    *  Where else can be implemented aside User Data?
    *    - Training Module Progress
    *    - User Settings
    *
    * */
    val userDataFlow = remember {
            userDataStoreManager.getFromDataStore()
                .asLiveData()
                .distinctUntilChanged()
    }

    val userData by userDataFlow.observeAsState()


    /*  [DATA RETRIEVAL FROM PERSISTENT DATA STORE - FAILED ATTEMPT #1 & #2]
    *   This is supposed to retrieve data from data store
    *   To retrieve data from data store, it needs to be in the coroutine scope
    *   Thus, Launched Effect is used.
    *
    *   Why failed?:
    *   - LaunchedEffect is only launched once at the first composition
    *   - As a result, every time app reloads, only the previous data is retrieved
    *
    * */
    /*    LaunchedEffect (Unit){

        Log.d("LAUNCHED - Retrieving from Datastore", "")

        userDataStoreManager.getFromDataStore()
            .asLiveData()
            .distinctUntilChanged()
            .observe(lifecycleOwner){ retrievedUser ->
            Log.d("LAUNCHED - Retrieve from Datastore", retrievedUser.toString())

            if (retrievedUser != null) {
                user.value = retrievedUser
            }

        }

    }*/


    /* [DATA RETRIEVAL FROM PERSISTENT DATA STORE - FAILED ATTEMPT #2]
    *  This is supposed to resolve Failed Attempt #1 from data store
    *
    *  Why Failed?
    *  - While it succeeds getting latest data, observe is stucked on a creation loop,
    *    which causes data to be emitted infinitely. From the words of codeium...
    *
    *    So, why does Code Snippet #2 emit the latest data infinitely?
    *    The reason is that observe is called every time the Composable function is recomposed,
    *    which can happen multiple times during the lifetime of the Composable function.
    *    Each time observe is called, it creates a new observer that observes the LiveData object
    *    and calls the callback function whenever the LiveData object emits a new value.
    *    Because distinctUntilChanged is used, the LiveData object will only emit a new value
    *    if the data has changed, but because observe is called multiple times,
    *    multiple observers are created, each of which will call the callback function whenever
    *    the LiveData object emits a new value. This can cause the callback function to be called
    *    multiple times, even if the data has not changed.
    */
    /*Log.d("LAUNCHED - Retrieving from Datastore", "")

    userDataStoreManager.getFromDataStore()
        .asLiveData()
        .distinctUntilChanged()
        .observe(LocalLifecycleOwner.current){ retrievedUser ->

        if(retrievedUser != null){
            user.value = retrievedUser
        }

    }*/



    Log.d("[Home Screen - Retrieved User]", "user2 = {${userData}}")


    // Qs:  How to make Column span the entire screen width?
    // Ans: By LocalConfiguration.current.screenWidthDp.dp
    Column(
        // Assign arrangement/alignment to children composables (i.e the two Text Composable below)
        // verticalArrangement = Arrangement.Center,
        // horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
//            .width(width)
            /*.padding(
                *//* This padding is to offset BottomAppBar height so that
                *  inner content is not hidden behind it when scrolled
                * *//*
                bottom = 35.dp
            )*/

    ) {

        userData?.let {
            SalutationContainer(
                username = it.name,
                modifier = Modifier
                    .background(Color(0xff00A19B))
                    .padding(
                        top = 20.dp
                    )
            )
        }


        QuickAccessBarContainer(
            onUserProfileButtonClicked = onUserProfileButtonClicked,
            onNotificationButtonClicked = onNotificationButtonClicked,
            modifier = Modifier
                .background(
                    color = Color(0xff00A19B),
                    shape = RectangleShape
                )
                .fillMaxWidth()
        )


        HorizontalDivider(color = Color(0xff00A190), thickness = 2.dp)

        OverviewSectionGroup()

        TabContainer(
            selectedTabIndex = 1,
            userData = userData,
            onTaskCardClicked = onTaskCardClicked,
            modifier = modifier
        )

    }


}

@Composable
fun SalutationContainer(
    username: String,
    modifier: Modifier = Modifier
){

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
    ){

        Column(
            modifier = modifier
        ){

            Text(
                text = "Welcome,",
                color = Color(0xffa4e8ef),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            if (username != null) {
                Text(
                    text = username,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold

                )
            }


        }

        ProfilePictureContainer(
            profilePicture = "",
            modifier = modifier
        )



    }


}

@Composable
fun QuickAccessBarContainer(
    onUserProfileButtonClicked: () -> Unit,
    onNotificationButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    /* Quick Access Text */
//    Text(
//        text = from,
//        fontSize = 20.sp,
//        color = Color.Black,
//        textAlign = TextAlign.Start,
//        fontWeight = FontWeight.Bold,
//        modifier = Modifier
//            .width(width)
//            .padding(16.dp)
//        // .align(alignment = Alignment.End)
//    )
    Column(
        modifier = modifier
    ) {

        /* Quick Access Bar */
        QuickAccessBar(
            onUserProfileButtonClicked = onUserProfileButtonClicked,
            onNotificationButtonClicked = onNotificationButtonClicked
        )

    }

}

// This composable serves to hold quick access links
@Composable
fun QuickAccessBar(
    onUserProfileButtonClicked: () -> Unit,
    onNotificationButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 12.dp,
                top = 0.dp,
                end = 12.dp,
                bottom = 0.dp
            )

    ) {

        // Instantiate array of icon images
//        var iconImages = arrayOf(
//            R.drawable.user_24,
//            R.drawable.envelope_24,
//            R.drawable.search_24,
//            R.drawable.settings_24,
//            R.drawable.calendar_24
//        )

        // Iteratively renders QuickAccessButton
//        iconImages.forEach { image ->
//
//            // Equal-width containers for four Quick Access Buttons
//            QuickAccessButton(
//                navController = navController,
//                iconImage = image,
//                containerColor = Color.White,
//                modifier = modifier.padding(5.dp)
//            )
//
//        }

        /* Quick Access: User Profile */
        QuickAccessButton(
            destination = {

                onUserProfileButtonClicked.invoke()

                Log.d("[Profile Clicked]","Clicked")
            },
            iconImage = R.drawable.user_24,
            containerColor = Color.White,
            modifier = modifier.padding(5.dp),
        )

        /* Quick Access: Notification */
        QuickAccessButton(
            destination = {
                onNotificationButtonClicked.invoke()
            },
            iconImage = R.drawable.envelope_24,
            containerColor = Color.White,
            modifier = modifier.padding(5.dp)
        )

        /* Quick Access: Search Button */
        QuickAccessButton(
            destination = {},
            iconImage = R.drawable.search_24,
            containerColor = Color.White,
            modifier = modifier.padding(5.dp)
        )

        /* Quick Access: Schedule Button */
        QuickAccessButton(
            destination = {},
            iconImage = R.drawable.calendar_24,
            containerColor = Color.White,
            modifier = modifier.padding(5.dp)
        )

        /* Quick Access: Settings Button */
        QuickAccessButton(
            destination = {},
            iconImage = R.drawable.settings_24,
            containerColor = Color.White,
            modifier = modifier.padding(5.dp)
        )

    }

}

// This composable serves to render individual links
@Composable
fun QuickAccessButton(
    destination: () -> Unit,
    iconImage: Int,
    modifier: Modifier = Modifier,
    containerColor: Color = Color(0xff00A19B)
) {

    Log.d("onClick","$destination")

    // Quick Access Button here
    FloatingActionButton(
        containerColor = Color(0xfff8f8f8),
        contentColor = Color(0xffffffff),
        elevation = FloatingActionButtonDefaults.elevation(9.dp),
        onClick = {
            destination()
            Log.d("FAB","Clicked")
        },
        modifier = modifier
    ) {

        // Instantiate Icon
        Icon(
            painterResource(iconImage),
            contentDescription = "Quick Access Button Prototype",
            modifier = modifier.size(25.dp),
            tint = Color(0xffc4c4c4)
        )

    }

}

/*
* Custom Tab Functions & Class
* */
class TabViewModel: ViewModel(){

    /* Outstanding Tab Selected State */
    private val _isOutstandingTabSelected: MutableLiveData<Boolean> = MutableLiveData(false)
    val isOutstandingTabSelected: LiveData<Boolean> = _isOutstandingTabSelected

    /* Completed Tab Selected State */
    private val _isCompletedTabSelected: MutableLiveData<Boolean> = MutableLiveData(false)
    val isCompletedTabSelected: LiveData<Boolean> = _isCompletedTabSelected


    /* Tab Selected Events -
    * Need both states to toggle with one another
    * */

    /* Outstanding Tab Selected State Handler */
    fun onOutstandingTabSelected(){

        if(_isOutstandingTabSelected.value == false){

            _isOutstandingTabSelected.value = true
            _isCompletedTabSelected.value = false

        }else{

            _isOutstandingTabSelected.value = false
            _isCompletedTabSelected.value = true

        }

        Log.d("[Tab Selected]", "$_isOutstandingTabSelected.value")

    }

    /* Completed Tab Selected State Handler */
    fun onCompletedTabSelected(){

        if(_isCompletedTabSelected.value == false){

            _isCompletedTabSelected.value = true
            _isOutstandingTabSelected.value = false


        }else{

            _isCompletedTabSelected.value = false
            _isOutstandingTabSelected.value = true


        }

        Log.d("[Tab Selected]", "$_isCompletedTabSelected.value")

    }

}

/* Custom Tab Container Composable */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TabContainer(
    selectedTabIndex: Any,
    onTaskCardClicked: NavHostController,
    /*userData: User?,*/
    userData: User?,
    modifier: Modifier = Modifier,
    tabViewModel: TabViewModel = viewModel(),
    taskViewModel: TasksViewModel = viewModel()
) {

    /* Hoisting state (isSelected)
    *  Think of it like RXJS Subscription (Dynamic data stream)
    *  */
    val isOutstandingTabSelected by tabViewModel.isOutstandingTabSelected.observeAsState(initial = false)
    val isCompletedTabSelected by tabViewModel.isCompletedTabSelected.observeAsState(initial = false)

    val taskMasterList = taskViewModel.getInitTaskList()


    val dummyTabLabels = listOf<String>(
        "Outstanding",
        "Completed"
    )

    FlowRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 0.dp,
                top = 20.dp,
                end = 0.dp,
                bottom = 18.dp
            )
//            .verticalScroll(rememberScrollState())
    ){

        /* Tabs Button */
        dummyTabLabels.forEachIndexed { index, tabLabel ->

            if( tabLabel == "Outstanding"){

                /* Outstanding Tab */
                Tab(
                    tabIndex = index + 1,
                    tabLabel = tabLabel,
                    isSelected = isOutstandingTabSelected,
                    onTabSelected = { tabViewModel.onOutstandingTabSelected() }
                )



            }else{

                /* Completed Tab */
                Tab(
                    tabIndex = index + 1,
                    tabLabel = tabLabel,
                    isSelected = isCompletedTabSelected,
                    onTabSelected = { tabViewModel.onCompletedTabSelected() }
                )

            }

        }

//        var iterableTaskList = taskMasterList.getAllTasks().listIterator()
        var iterableIndex: Int = 0

        /* [26/11/2024 DATA STORE RETRIEVAL] */
        var iterableTaskList = userData?.allTasks?.outstanding?.listIterator()

        Column(
            modifier = Modifier
                .padding(10.dp)
                .verticalScroll(rememberScrollState())

        ){

            if (iterableTaskList != null) {

                for(task in iterableTaskList){

                    /* Tabs Contents */
                    TabContent(

                        canShowContent = isCompletedTabSelected,
                        task = task,
                        taskIndex = iterableIndex,
                        onTaskCardClicked = onTaskCardClicked

                    )

                    iterableIndex++

                }
            }

        }

        iterableIndex = 0

    }

}

/* Custom Tab Composable */
/* Remember: Composable should be stateless, to send these states down to Tab -:
*   (1) Select state (boolean)
*     - Change Tab colour when selected, grey it out when not
*  */
@Composable
fun Tab(
    tabIndex: Int = 0,
    tabLabel: String = "Tab Label",
    isSelected: Boolean,
    onTabSelected: () -> Unit,
    modifier: Modifier = Modifier.padding(
        start = 0.dp,
        top = 0.dp,
        end = 0.dp,
        bottom = 0.dp
    )
) {


    Log.d("[Tab Selected]", "$onTabSelected")
    Surface(
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(3.dp)
    ){

        Button(
            onClick = onTabSelected,
            shape = RoundedCornerShape(20.dp),
            colors = ButtonColors(
                containerColor = Color(0xff00736f),
                contentColor = Color(0xffffffff),
                disabledContentColor = Color(0xff00736f),
                disabledContainerColor = Color(0xff00736f)),
            modifier = Modifier
                .fillMaxWidth(0.48f)
                .background(Color(0xff00736f), RoundedCornerShape(12.dp))
        ){
            Text(
                text = tabLabel,
                modifier = modifier
            )

            Text(
                text= " $isSelected",
                modifier = modifier
            )
        }


    }


    
}

// =========================================
// Task Card Button
// =========================================
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TabContent(
    canShowContent: Boolean,
    /*task: FieldTask,*/
    task: Task,
    taskIndex: Int,
    onTaskCardClicked: NavHostController,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .border(
            border = BorderStroke(2.dp, Color(0xfffffed4)),
            shape = RoundedCornerShape(12.dp),
        )
        .background(Color(0xfffffed4), RoundedCornerShape(12.dp))
        .padding(
            5.dp,
            5.dp,
            5.dp,
            5.dp
        ),
    taskViewModel: TasksViewModel = viewModel()

){

    Surface(
        shadowElevation = 5.dp,
        shape = RoundedCornerShape(12.dp),
        color = Color(0xfffffed4),
        modifier = Modifier.padding(5.dp),
        onClick = {

            /* Navigate and pass data over into Task Detail Start Screen
            *   TASK_ID = Task Card Array Index
            *   STEP_ID = Next Step of a Task (WIP)
            * */
            onTaskCardClicked.navigate(
                route = Screen.TaskDetailStart.passTaskIDandStepID(

                    TASK_ID = task.taskID

                )
            )

        }
    ) {

        FlowColumn(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
        ){

            Text(
                text = "#${taskIndex} ${task.action} (${task.priority})",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight(800),
                modifier = modifier.padding(
                    start = 20.dp,
                    top = 10.dp,
                    end = 0.dp,
                    bottom = 0.dp
                )
            )

            Text(
                text = "Location ${task.location}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight(400),
                modifier = modifier.padding(
                    start = 20.dp,
                    top = 0.dp,
                    end = 0.dp,
                    bottom = 0.dp
                )
            )

            Text(
                text = task.taskFor,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight(400),
                modifier = modifier.padding(
                    start = 20.dp,
                    top = 0.dp,
                    end = 0.dp,
                    bottom = 0.dp
                )
            )

            if(task.completed){

                Text(
                    text = "Completed",
                    fontWeight = FontWeight(400),
                    modifier = modifier.padding(
                        start = 20.dp,
                        top = 0.dp,
                        end = 0.dp,
                        bottom = 0.dp
                    )
                )

            }else{

                Text(
                    text = "Incomplete",
                    fontWeight = FontWeight(400),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = modifier.padding(
                        start = 20.dp,
                        top = 0.dp,
                        end = 0.dp,
                        bottom = 0.dp
                    )
                )

            }

//        }


        }

    }





}

/*@Composable
fun BottomBar(navController: NavController){
    val screens = listOf(
        BottomBarScreen.Back,
        BottomBarScreen.Home,
        BottomBarScreen.Forward
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var currentDestination = navBackStackEntry?.destination

    BottomNavigation{

        screens.forEach{ screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavController
) {

    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },

        *//* If current destination in record (hierarchy) is equal
           to one of the screen routes, deem it as selected *//*
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route)
        }
    )

}*/





@Composable
@PreviewScreenSizes()
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    HomeScreen(
        onUserProfileButtonClicked = {},
        onNotificationButtonClicked = {},
        onTaskCardClicked = rememberNavController()
    )
}