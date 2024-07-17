package com.example.kotlindevcourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
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
import androidx.navigation.fragment.NavHostFragment
import com.example.kotlindevcourse.ui.theme.KotlinDevCourseTheme
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.*


class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    // The main()
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // When UI Rendering takes place
        setContent {
            KotlinDevCourseTheme {

                navController = rememberNavController()
                SetupNavGraph(navController = navController)

//                Scaffold(
//
//                    topBar = {
//                        TopAppBar(
//
//                            colors = topAppBarColors(
//                                containerColor = Color(0xff00A19B),
//                                titleContentColor = MaterialTheme.colorScheme.primary
//                            ),
//                            title = {
//                                Text(
//                                    text = "Field Work Ecosystem",
//                                    fontWeight = FontWeight.ExtraBold,
//                                    color = Color(0xffffffff)
//                                )
//                            }
//
//                        )
//                    },
//                    bottomBar = {
//                        BottomAppBar(
//                            containerColor = Color(0xff00A19B),
//                            contentColor = MaterialTheme.colorScheme.primary
//                        ){
//
//                            FlowRow(
//                                modifier = Modifier.fillMaxWidth(),
//                                horizontalArrangement = Arrangement.SpaceEvenly
//                            ) {
//
//                                // Instantiate Icon
//                                val backArrowIconImage = R.drawable.arrow_back
//                                val forwardArrowIconImage = R.drawable.arrow_forward
//                                val menuIconImage = R.drawable.menu
//
//                                /* Back Arrow */
//                                Button(
//                                    onClick = { /*TODO*/ },
//                                    colors = ButtonColors(
//                                        containerColor = Color(0xff00A19B),
//                                        disabledContainerColor = Color(0xff00A19B),
//                                        contentColor = Color(0xffffffff),
//                                        disabledContentColor = Color(0xffffffff)
//                                    )
//                                ) {
//
//                                    Icon(
//                                        painterResource(backArrowIconImage),
//                                        contentDescription = "Quick Access Button Prototype",
//                                        modifier = Modifier.size(45.dp)
//                                    )
//
//                                }
//
//                                /* Menu Arrow */
//                                Button(
//                                    onClick = { /*TODO*/ },
//                                    colors = ButtonColors(
//                                        containerColor = Color(0xff00A19B),
//                                        disabledContainerColor = Color(0xff00A19B),
//                                        contentColor = Color(0xffffffff),
//                                        disabledContentColor = Color(0xffffffff)
//                                    )
//                                    ) {
//
//                                    Icon(
//                                        painterResource(menuIconImage),
//                                        contentDescription = "Quick Access Button Prototype",
//                                        modifier = Modifier.size(45.dp)
//                                    )
//
//                                }
//
//                                /* Forward Arrow*/
//                                Button(
//                                    onClick = { /*TODO*/ },
//                                    colors = ButtonColors(
//                                        containerColor = Color(0xff00A19B),
//                                        disabledContainerColor = Color(0xff00A19B),
//                                        contentColor = Color(0xffffffff),
//                                        disabledContentColor = Color(0xffffffff)
//                                    )
//                                ) {
//
//                                    Icon(
//                                        painterResource(forwardArrowIconImage),
//                                        contentDescription = "Quick Access Button Prototype",
//                                        modifier = Modifier.size(45.dp)
//                                    )
//                                }
//                            }
//
////                            Text(
////                                modifier = Modifier
////                                    .fillMaxWidth()~,
////                                textAlign = TextAlign.Center,
////                                text = "Dumb App Inc."
////                            )
//
//                        }
//                    }
//                ){
//
//                    innerPadding ->
//                    Column(
//                       modifier = Modifier.padding(innerPadding)
//                    ){
//
//                        val configuration = LocalConfiguration.current
//                        val screenWidth: Dp = configuration.screenWidthDp.dp
//                        val screenHeight: Dp = configuration.screenHeightDp.dp
//
//                        BodyContent(
//                            width = screenWidth,
//                            height = screenHeight,
//                            message = "Body Content",
//                            from = "Quick Access",
//                            modifier = Modifier
//                        )
//
//                        /* [NOT IN USE] Search Bar */
//                        // SearchBarFormGroup()
//                        /* [NOT IN USE] Username Field*/
//                        // UserCredentialFormGroup()
//
//                    }

            }

        }

    }
}


//class FieldTasksViewModel: ViewModel(){
//
//    private val _numOfOutstandingTasks: MutableLiveData<Int> = MutableLiveData(6)
//
//    val numOfOutstandingTasks: LiveData<Int> = _numOfOutstandingTasks
//
//    /* [Note] Wire this up with the function that serves updated data
//    *         originated from the backend
//    *  */
//    fun onDataChange(updatedNumOfOutstandingTasks: Int){
//
//        _numOfOutstandingTasks.value = updatedNumOfOutstandingTasks
//
//    }
//
//}
//
//@Composable
//fun OverviewSectionGroup(fieldTaskViewModel: FieldTasksViewModel = viewModel()){
//
//    /* State subscription vars here */
//    val numOfOutstandingTasks: Int by fieldTaskViewModel.numOfOutstandingTasks.observeAsState(
//        initial = 0
//    )
//
//    /* Instantiate Overview Section with state subscription vars wired up */
//    OverviewSection(
//        numOfOutstandingTasks = numOfOutstandingTasks
//    )
//
//}
//
//@OptIn(ExperimentalLayoutApi::class)
//@Composable
//fun OverviewSection(
//    numOfOutstandingTasks: Int,
//    modifier: Modifier = Modifier
//){
//
//    FlowRow(
//        modifier = modifier
//            .padding(16.dp)
//            .fillMaxWidth()
//            /* Layout Guideline */
//            .border(
//                border = BorderStroke(0.dp, Color.LightGray),
//                shape = RectangleShape
//            ),
//        horizontalArrangement = Arrangement.SpaceEvenly
//    ){
//
//        // Outstanding Tasks Number here
//        OverviewNumberCard(
//            numOfOutstandingTasks = numOfOutstandingTasks,
//        )
//
//        // Should provide a column of num breakdowns of outstanding tasks
//        FlowColumn(
//            horizontalArrangement = Arrangement.End,
//            modifier = modifier.padding(
//                0.dp,
//                0.dp
//            ),
//        ){
//
//            Text(
//                text = "2 lube oil change",
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier
//                    .padding(0.dp, 0.dp, 0.dp, 4.dp)
//                    .border(
//                        border = BorderStroke(1.dp, Color(0xffe0c249)),
//                        shape = RoundedCornerShape(12.dp),
//                    )
//                    .background(Color(0xffe0c249), RoundedCornerShape(12.dp))
//                    .padding(10.dp)
//
//
//            )
//            //            HorizontalDivider()
//            Text(
//                text = "2 pump shutdown",
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier
//                    .padding(0.dp, 8.dp, 0.dp, 4.dp)
//                    .border(
//                        border = BorderStroke(1.dp, Color(0xffe38329)),
//                        shape = RoundedCornerShape(12.dp),
//                    )
//                    .background(Color(0xffe38329), RoundedCornerShape(12.dp))
//                    .padding(10.dp)
//
//            )
//            //            HorizontalDivider()
//            Text(
//                text = "2 isolation works",
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier
//                    .padding(0.dp, 8.dp, 0.dp, 8.dp)
//                    .border(
//                        border = BorderStroke(1.dp, Color(0xffee87f5)),
//                        shape = RoundedCornerShape(12.dp),
//                    )
//                    .background(Color(0xffee87f5), RoundedCornerShape(12.dp))
//                    .padding(10.dp)
//            )
//            //            HorizontalDivider()
//
//
//        }
//    }
//
//}
//
//@Composable
//fun OverviewNumberCard(
//    numOfOutstandingTasks: Int,
//    modifier: Modifier = Modifier
//){
//
//    Column(
//        modifier = Modifier
//            .border(
//                border = BorderStroke(2.dp, Color(0xff4370cf)),
//                shape = RoundedCornerShape(12.dp),
//            )
//            .background(Color(0xff4370cf), RoundedCornerShape(12.dp))
//            .padding(
//                60.dp,
//                20.dp
//            )
//
//    ){
//
//        /* Define sub composables */
//        Text(
//            text = "$numOfOutstandingTasks",
//            fontWeight = FontWeight.ExtraBold,
//            fontSize = 80.sp,
//            modifier = modifier,
//            style = MaterialTheme.typography.headlineLarge,
//            color = Color.White
//        )
//
//        /* Define sub composables */
//        Text(
//            text = "tasks",
//            fontWeight = FontWeight.ExtraBold,
//            fontSize = 20.sp,
//            style = MaterialTheme.typography.labelLarge,
//            color = Color.White
//        )
//
//
//
//    }
//
//
//
//    /* Outline event handler that changes state of this composable */
//
//
//}
//
////Composable Function that can be used by setContent()
//@Composable
//fun BodyContent(width: Dp,
//                height: Dp,
//                message: String,
//                from: String,
//                modifier: Modifier = Modifier) {
//
//    // Qs:  How to make Column span the entire screen width?
//    // Ans: By LocalConfiguration.current.screenWidthDp.dp
//    Column(
//        // Assign arrangement/alignment to children composables (i.e the two Text Composable below)
//        // verticalArrangement = Arrangement.Center,
//        // horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier
//            .width(width)
//            // .background(Color(0xFFFFFFFF))
//
//    ){
//
//        /* Quick Access Text */
//        Text(
//            text = from,
//            fontSize = 20.sp,
//            color = Color.Black,
//            textAlign = TextAlign.Start,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier
//                .width(width)
//                .padding(16.dp)
//            // .align(alignment = Alignment.End)
//        )
//
//        /* Quick Access Bar */
//        QuickAccessBar()
//
//        HorizontalDivider(color = Color.Gray, thickness = 1.dp)
//
//        OverviewSectionGroup()
//
//
//
//    }
//
//
//}
//
//// This composable serves to hold quick access links
//@Composable
//fun QuickAccessBar(
//    modifier: Modifier = Modifier){
//
//    Row(
//        modifier = modifier.padding(
//            start = 12.dp,
//            top = 0.dp,
//            end = 0.dp,
//            bottom = 16.dp
//        )
//    ){
//
//        // Instantiate array of icon images
//        var iconImages = arrayOf(
//            R.drawable.user_24,
//            R.drawable.envelope_24,
//            R.drawable.search_24,
//            R.drawable.settings_24,
//            R.drawable.calendar_24
//        )
//
//        // Iteratively renders QuickAccessButton
//        iconImages.forEach {
//            image ->
//
//            // Equal-width containers for four Quick Access Buttons
//            QuickAccessButton(
//                iconImage = image,
//                containerColor = Color.White,
//                modifier = modifier.padding(5.dp)
//            )
//
//        }
//
//    }
//
//}
//
//// This composable serves to render individual links
//@Composable
//fun QuickAccessButton(
//    iconImage: Int,
//    modifier: Modifier = Modifier,
//    containerColor: Color = Color(0xff00A19B),
//    ){
//
//    // Quick Access Button here
//    FloatingActionButton(
//        containerColor = Color(0xff00736f),
//        contentColor = Color(0xffffffff),
//        elevation = FloatingActionButtonDefaults.elevation(16.dp),
//        onClick = { /*TODO*/ },
//        modifier = modifier.size(75.dp)
//    ) {
//
//        // Instantiate Icon
//        Icon(
//            painterResource(iconImage),
//            contentDescription = "Quick Access Button Prototype",
//            modifier = modifier.size(45.dp)
//        )
//
//    }
//
//}


/* ==================================================================== */

/* [NOT IN USE] To keep for learning */
/* A View Model Class to handle (store & access) of search query states */
class SearchViewModel: ViewModel(){

    /* Private var, accessible only by this class, to hold state changes of search query
    *  This will be a private storage for mutable search queries */
    private val _searchQuery = MutableLiveData("")

    /* Instantiate a public var to store Live Data
       This will be observable as a state in composables */
    var searchQuery: LiveData<String> = _searchQuery

    /* A callback to set private var based on change in search query values */
    fun onSearchQueryChange(newSearchQuery: String){
        _searchQuery.value = newSearchQuery
    }


}


/* [NOT IN USE] To keep for learning */
/* [Working Prototype of ViewModel] Structured as a subclass of ViewModel() */
class UserCredentialViewModel: ViewModel(){

    /* Private var */
    private val _username: MutableLiveData<String> = MutableLiveData("")

    /* Public var - wired with callee var (in UserCredentialFormGroup) */
    val username: LiveData<String> = _username

    /* Event Handler for Username change */
    fun onUsernameChange(newUsername: String){

        _username.value = newUsername

    }

}

/* [NOT IN USE] To keep for learning */
@Composable
fun UserCredentialFormGroup(userCredentialViewModel: UserCredentialViewModel = viewModel()){

    val username: String by userCredentialViewModel.username.observeAsState("")

    UserNameField(username = username, onUsernameChange = { userCredentialViewModel.onUsernameChange(it) })

}

/* [NOT IN USE] To keep for learning */
@Composable
fun UserNameField(
    username: String,
    onUsernameChange: (String) -> Unit,
    modifier: Modifier = Modifier
){

    OutlinedTextField(
        value = username,
        onValueChange = onUsernameChange,
        label = { Text("Username")},
        modifier = modifier.padding(
            start = 12.dp,
            top = 0.dp,
            end = 0.dp,
            bottom = 6.dp
        )
    )

}

/* [NOT IN USE] To keep for learning */
@Composable
//fun SearchHandler(searchViewModel: SearchViewModel = viewModel()){
fun SearchBarFormGroup(){

    /* State variable of user inputs in respective fields */
    var searchQuery: String by rememberSaveable{mutableStateOf("")}
    var areaId: String by rememberSaveable{ mutableStateOf("") }

    /* Instantiate Search Bar Composable */
    SearchBar(
        searchQuery = searchQuery,
        onSearchQueryChange = { searchQuery = it },
        // onSearchQueryChange = { searchViewModel.onSearchQueryChange(it)}
    )

    /* Instantiate Area Id Bar Composable*/
    AreaIdBar(
        areaId = areaId,
        onAreaIdChange = { areaId = it }
    )



}

/* [NOT IN USE] To keep for learning */
@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
){

    Text(
        text = searchQuery,
        modifier = Modifier.padding(
            start = 12.dp,
            top = 6.dp,
            end = 0.dp,
            bottom = 0.dp
        ),
        style = MaterialTheme.typography.titleMedium
    )

    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        label = { Text("Search")},
        modifier = modifier
            .padding(
                start = 12.dp,
                top = 0.dp,
                end = 12.dp,
                bottom = 6.dp
            )
            .fillMaxWidth(),
        shape = RoundedCornerShape(
            12.dp
        )

    )

}

/* [NOT IN USE] To keep for learning */
@Composable
fun AreaIdBar(
    areaId: String,
    onAreaIdChange: (String) -> Unit,
    modifier: Modifier = Modifier
){

    Text(
        text = areaId,
        modifier = Modifier.padding(
            start = 12.dp,
            top = 6.dp,
            end = 0.dp,
            bottom = 0.dp
        ),
        style = MaterialTheme.typography.titleMedium
    )

    OutlinedTextField(
        value = areaId,
        onValueChange = onAreaIdChange,
        label = { Text("Area ID")},
        modifier = modifier.padding(
            start = 12.dp,
            top = 0.dp,
            end = 0.dp,
            bottom = 16.dp
        )

    )

}

// Preview function of our composable so that we can peek into the design without scaffolding
// the entire code
@Preview(showBackground = true)
@Composable
fun BodyContentPreview() {

    val configuration = LocalConfiguration.current
    val screenWidth: Dp = configuration.screenWidthDp.dp
    val screenHeight: Dp = configuration.screenHeightDp.dp

    KotlinDevCourseTheme {
        BodyContent(
            navController = rememberNavController(),
            width = screenWidth,
            height = screenHeight,
            message = "Body Content",
            from = "Quick Access")
    }
}