package com.example.kotlindevcourse

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onUserProfileButtonClicked: () -> Unit,
    onNotificationButtonClicked: () -> Unit
) {

    Scaffold(

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
                        onClick = onUserProfileButtonClicked,
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
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {

            val configuration = LocalConfiguration.current
            val screenWidth: Dp = configuration.screenWidthDp.dp
            val screenHeight: Dp = configuration.screenHeightDp.dp

            BodyContent(
                onUserProfileButtonClicked = onUserProfileButtonClicked,
                onNotificationButtonClicked = onNotificationButtonClicked,
                width = screenWidth,
                height = screenHeight,
                message = "Body Content",
                from = "Quick Access",
                modifier = Modifier
            )

            /* [NOT IN USE] Search Bar */
            // SearchBarFormGroup()
            /* [NOT IN USE] Username Field*/
            // UserCredentialFormGroup()

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
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            /* Layout Guideline */
            .border(
                border = BorderStroke(0.dp, Color.LightGray),
                shape = RectangleShape
            ),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        // Outstanding Tasks Number here
        OverviewNumberCard(
            numOfOutstandingTasks = numOfOutstandingTasks,
        )

        // Should provide a column of num breakdowns of outstanding tasks
        FlowColumn(
            horizontalArrangement = Arrangement.End,
            modifier = modifier.padding(
                0.dp,
                0.dp
            ),
        ) {

            Text(
                text = "2 lube oil change",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 4.dp)
                    .border(
                        border = BorderStroke(1.dp, Color(0xffe0c249)),
                        shape = RoundedCornerShape(12.dp),
                    )
                    .background(Color(0xffe0c249), RoundedCornerShape(12.dp))
                    .padding(10.dp)


            )
            //            HorizontalDivider()
            Text(
                text = "2 pump shutdown",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(0.dp, 8.dp, 0.dp, 4.dp)
                    .border(
                        border = BorderStroke(1.dp, Color(0xffe38329)),
                        shape = RoundedCornerShape(12.dp),
                    )
                    .background(Color(0xffe38329), RoundedCornerShape(12.dp))
                    .padding(10.dp)

            )
            //            HorizontalDivider()
            Text(
                text = "2 isolation works",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(0.dp, 8.dp, 0.dp, 8.dp)
                    .border(
                        border = BorderStroke(1.dp, Color(0xffee87f5)),
                        shape = RoundedCornerShape(12.dp),
                    )
                    .background(Color(0xffee87f5), RoundedCornerShape(12.dp))
                    .padding(10.dp)
            )
            //            HorizontalDivider()


        }
    }

}

@Composable
fun OverviewNumberCard(
    numOfOutstandingTasks: Int,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier
            .border(
                border = BorderStroke(2.dp, Color(0xff4370cf)),
                shape = RoundedCornerShape(12.dp),
            )
            .background(Color(0xff4370cf), RoundedCornerShape(12.dp))
            .padding(
                60.dp,
                20.dp
            )

    ) {

        /* Define sub composables */
        Text(
            text = "$numOfOutstandingTasks",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 80.sp,
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
            color = Color.White
        )


    }


    /* Outline event handler that changes state of this composable */


}

//Composable Function that can be used by setContent()
@Composable
fun BodyContent(
    onUserProfileButtonClicked: () -> Unit,
    onNotificationButtonClicked: () -> Unit,
    width: Dp,
    height: Dp,
    message: String,
    from: String,
    modifier: Modifier = Modifier
) {

    // Qs:  How to make Column span the entire screen width?
    // Ans: By LocalConfiguration.current.screenWidthDp.dp
    Column(
        // Assign arrangement/alignment to children composables (i.e the two Text Composable below)
        // verticalArrangement = Arrangement.Center,
        // horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(width)
        // .background(Color(0xFFFFFFFF))

    ) {

        /* Quick Access Text */
        Text(
            text = from,
            fontSize = 20.sp,
            color = Color.Black,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .width(width)
                .padding(16.dp)
            // .align(alignment = Alignment.End)
        )

        /* Quick Access Bar */
        QuickAccessBar(
            onUserProfileButtonClicked = onUserProfileButtonClicked,
            onNotificationButtonClicked = onNotificationButtonClicked
        )

        HorizontalDivider(color = Color.Gray, thickness = 1.dp)

        OverviewSectionGroup()

        TabContainer(
            selectedTabIndex = 1,
            divider = {},
            modifier = modifier
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
        modifier = modifier.padding(
            start = 12.dp,
            top = 0.dp,
            end = 0.dp,
            bottom = 16.dp
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
            onClick = onUserProfileButtonClicked,
            iconImage = R.drawable.user_24,
            containerColor = Color.White,
            modifier = modifier.padding(5.dp),
        )

        /* Quick Access: Notification */
        QuickAccessButton(
            onClick = onNotificationButtonClicked,
            iconImage = R.drawable.envelope_24,
            containerColor = Color.White,
            modifier = modifier.padding(5.dp)
        )

        /* Quick Access: Search Button */
        QuickAccessButton(
            onClick = {},
            iconImage = R.drawable.search_24,
            containerColor = Color.White,
            modifier = modifier.padding(5.dp)
        )

        /* Quick Access: Schedule Button */
        QuickAccessButton(
            onClick = {},
            iconImage = R.drawable.calendar_24,
            containerColor = Color.White,
            modifier = modifier.padding(5.dp)
        )

        /* Quick Access: Settings Button */
        QuickAccessButton(
            onClick = {},
            iconImage = R.drawable.settings_24,
            containerColor = Color.White,
            modifier = modifier.padding(5.dp)
        )

    }

}

// This composable serves to render individual links
@Composable
fun QuickAccessButton(
    onClick: () -> Unit,
    iconImage: Int,
    modifier: Modifier = Modifier,
    containerColor: Color = Color(0xff00A19B)
) {

    // Quick Access Button here
    FloatingActionButton(
        containerColor = Color(0xff00736f),
        contentColor = Color(0xffffffff),
        elevation = FloatingActionButtonDefaults.elevation(16.dp),
        onClick = onClick,
        modifier = modifier.size(75.dp)
    ) {

        // Instantiate Icon
        Icon(
            painterResource(iconImage),
            contentDescription = "Quick Access Button Prototype",
            modifier = modifier.size(45.dp)
        )

    }

}


/* Custom Tab Container Composable */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TabContainer(
    selectedTabIndex: Any,
    divider: Any,
    modifier: Modifier = Modifier
) {

    val dummyTabLabels = listOf<String>(
        "Outstanding",
        "Completed"
    )

    FlowRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth()
    ){

        dummyTabLabels.forEachIndexed { index, tabLabel ->

            Tab(
                tabIndex = index + 1,
                tabLabel = tabLabel
            )

        }

    }

}

/* Custom Tab Composable */
@Composable
fun Tab(
    tabIndex: Int = 0,
    tabLabel: String = "Tab Label",
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {},
        modifier = modifier.width(170.dp)
    ){
        Text(
            text = tabLabel,
            modifier = modifier
        )
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
@Preview(showBackground = true)
fun HomeScreenPreview(modifier: Modifier = Modifier) {
    HomeScreen(
        onUserProfileButtonClicked = {},
        onNotificationButtonClicked = {}
    )
}