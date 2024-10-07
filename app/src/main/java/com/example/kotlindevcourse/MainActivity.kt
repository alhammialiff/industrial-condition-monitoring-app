package com.example.kotlindevcourse

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kotlindevcourse.ui.theme.KotlinDevCourseTheme
//import com.example.kotlindevcourse.navigation.RootNavGraph
import com.example.kotlindevcourse.navigation.SetupNavGraph


class MainActivity : ComponentActivity() {

    lateinit var navHostController: NavHostController


    // The main()
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // When UI Rendering takes place
        setContent {

            navHostController = rememberNavController()
            KotlinDevCourseTheme {

                SetupNavGraph(navHostController)
                Log.d("Initializing Root Nav Graph"," - ")


            }
        }
    }
}


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

//        BodyContent(
//            onUserProfileButtonClicked = {},
//            onNotificationButtonClicked = {},
//            width = screenWidth,
//            height = screenHeight,
//            message = "Body Content",
//            from = "Quick Access"
//        )

    }

}