package com.example.kotlindevcourse.states

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.kotlindevcourse.ActionItemsToDelete
import com.example.kotlindevcourse.FieldTaskToDelete
import com.example.kotlindevcourse.Screen
import com.example.kotlindevcourse.TaskStepToDelete
import com.example.kotlindevcourse.UserToDelete
import com.example.kotlindevcourse.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class UserViewModel: ViewModel() {

    /* [DUMMY USER DATA]
    *  Data is prepped for consumption over here via
    *  mutableListOf.
    *
    *  Data takes the form of TaskList class
    *  [!!] This list should be in DB in prod,
    *       and only authenticated user is served by the Backend
    *  */
    private var userList = mutableListOf(
        UserToDelete(
            id = "1111",
            username = "stephanbodzin",
            name = "Stephan Bodzin",
            email = "stephan.bodzin@email.com",
            role = "user",
            department = "Electrical",
            designation = "Electrician",
            actionItemsToDelete = ActionItemsToDelete(
                outstanding = mutableListOf(
                    FieldTaskToDelete(
                        0,
                        "Lube Oil Change",
                        arrayOf(
                            TaskStepToDelete(
                                0,
                                "Unscrew Oiler Cap from Oiler"
                            ),
                            TaskStepToDelete(
                                1,
                                "Refill Lube Oil till the Max line"
                            ),
                            TaskStepToDelete(
                                2,
                                "Screw Oiler Cap back onto Oiler"
                            ),
                        ),
                        "23/07/2024",
                        "Area A-2",
                        "high",
                        "Area B-4",
                        false
                    )
                ),
                ongoing = mutableListOf(
                    FieldTaskToDelete(
                        1,
                        "Check Pump Flow Rate",
                        arrayOf(
                            TaskStepToDelete(
                                0,
                                "Unscrew Oiler Cap from Oiler"
                            ),
                            TaskStepToDelete(
                                1,
                                "Refill Lube Oil till the Max line"
                            ),
                            TaskStepToDelete(
                                2,
                                "Screw Oiler Cap back onto Oiler"
                            ),
                        ),
                        "23/07/2024",
                        "Area B-1",
                        "low",
                        "Area B-4",
                        false
                    )
                ),
                completed = mutableListOf(
                    FieldTaskToDelete(
                        2,
                        "Examine Furnace",
                        arrayOf(
                            TaskStepToDelete(
                                0,
                                "Unscrew Oiler Cap from Oiler"
                            ),
                            TaskStepToDelete(
                                1,
                                "Refill Lube Oil till the Max line"
                            ),
                            TaskStepToDelete(
                                2,
                                "Screw Oiler Cap back onto Oiler"
                            ),
                        ),
                        "23/07/2024",
                        "Area A-1",
                        "high",
                        "Area B-2",
                        true
                    )
                )
            ),
            latestActivity = Screen.Home.route,
            lastLoggedIn = "[TODO]",
            lastLoggedOut = "[TODO]"
        ),
        UserToDelete(
            id = "2222",
            username = "tadaoando",
            name = "Tadao Ando",
            email = "tadao.ando@email.com",
            role = "user",
            department = "Facilities Management",
            designation = "Field Operator",
            actionItemsToDelete = ActionItemsToDelete(
                outstanding = mutableListOf(
                    FieldTaskToDelete(
                        4,
                        "Examine Fire Extinguisher Rack",
                        arrayOf(
                            TaskStepToDelete(
                                0,
                                "Unscrew Oiler Cap from Oiler"
                            ),
                            TaskStepToDelete(
                                1,
                                "Refill Lube Oil till the Max line"
                            ),
                            TaskStepToDelete(
                                2,
                                "Screw Oiler Cap back onto Oiler"
                            ),
                        ),
                        "23/07/2024",
                        "Fire Truck Shed",
                        "mid",
                        "Fire Truck Shed",
                        false
                    )
                ),
                ongoing = mutableListOf(
                    FieldTaskToDelete(
                        2,
                        "Check Smart Pump Meter Reader",
                        arrayOf(
                            TaskStepToDelete(
                                0,
                                "Unscrew Oiler Cap from Oiler"
                            ),
                            TaskStepToDelete(
                                1,
                                "Refill Lube Oil till the Max line"
                            ),
                            TaskStepToDelete(
                                2,
                                "Screw Oiler Cap back onto Oiler"
                            ),
                        ),
                        "23/07/2024",
                        "Area C-1",
                        "high",
                        "Area C-1",
                        false
                    )
                ),
                completed = mutableListOf(
                    FieldTaskToDelete(
                        2,
                        "Examine Furnace",
                        arrayOf(
                            TaskStepToDelete(
                                0,
                                "Unscrew Oiler Cap from Oiler"
                            ),
                            TaskStepToDelete(
                                1,
                                "Refill Lube Oil till the Max line"
                            ),
                            TaskStepToDelete(
                                2,
                                "Screw Oiler Cap back onto Oiler"
                            ),
                        ),
                        "23/07/2024",
                        "Area C-5",
                        "high",
                        "Area C-5",
                        true
                    )
                ),

            ),
            latestActivity = Screen.Home.route,
            lastLoggedIn = "[TODO]",
            lastLoggedOut = "[TODO]"
        ),
        UserToDelete(
            id = "3333",
            username = "johnroti",
            name = "John Roti",
            email = "john.roti@email.com",
            role = "user",
            department = "Process Operations",
            designation = "Process Technician",
            actionItemsToDelete = ActionItemsToDelete(
                outstanding = mutableListOf(
                    FieldTaskToDelete(
                        2,
                        "Examine Pump Electrical DB",
                        arrayOf(
                            TaskStepToDelete(
                                0,
                                "Unscrew Oiler Cap from Oiler"
                            ),
                            TaskStepToDelete(
                                1,
                                "Refill Lube Oil till the Max line"
                            ),
                            TaskStepToDelete(
                                2,
                                "Screw Oiler Cap back onto Oiler"
                            ),
                        ),
                        "23/07/2024",
                        "Processing A - M2",
                        "high",
                        "Area D-1",
                        false
                    )
                ),
                ongoing = mutableListOf(
                    FieldTaskToDelete(
                        2,
                        "Examine Machine Line Current Stability",
                        arrayOf(
                            TaskStepToDelete(
                                0,
                                "Unscrew Oiler Cap from Oiler"
                            ),
                            TaskStepToDelete(
                                1,
                                "Refill Lube Oil till the Max line"
                            ),
                            TaskStepToDelete(
                                2,
                                "Screw Oiler Cap back onto Oiler"
                            ),
                        ),
                        "23/07/2024",
                        "Processing B M1",
                        "high",
                        "Area D-1",
                        false
                    )
                ),
                completed = mutableListOf(
                    FieldTaskToDelete(
                        2,
                        "Examine Machine Line Current Stability",
                        arrayOf(
                            TaskStepToDelete(
                                0,
                                "Unscrew Oiler Cap from Oiler"
                            ),
                            TaskStepToDelete(
                                1,
                                "Refill Lube Oil till the Max line"
                            ),
                            TaskStepToDelete(
                                2,
                                "Screw Oiler Cap back onto Oiler"
                            ),
                        ),
                        "23/07/2024",
                        "Processing B M4",
                        "mid",
                        "Area D-1",
                        true
                    )
                )
            ),
            latestActivity = Screen.Home.route,
            lastLoggedIn = "[TODO]",
            lastLoggedOut = "[TODO]"
        ),
    )

    /* Will be initialize later when variable finally has value */
    var _thisUser: MutableState<UserToDelete> = mutableStateOf(UserToDelete(
        id = "-1111",
        username = "",
        name = "",
        email = "",
        role = "",
        department = "",
        designation = "",
        actionItemsToDelete = ActionItemsToDelete(
            outstanding = mutableListOf(
                FieldTaskToDelete(
                    taskID = -1,
                    action = "",
                    taskStepToDeletes = arrayOf<TaskStepToDelete>(
                        TaskStepToDelete(
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
                FieldTaskToDelete(
                    taskID = -1,
                    action = "",
                    taskStepToDeletes = arrayOf<TaskStepToDelete>(
                        TaskStepToDelete(
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
                FieldTaskToDelete(
                    taskID = -1,
                    action = "",
                    taskStepToDeletes = arrayOf<TaskStepToDelete>(
                        TaskStepToDelete(
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
    ))
    
/*    var thisUser: User = User(
        username = TODO(),
        name = TODO(),
        email = TODO(),
        role = TODO(),
        department = TODO(),
        designation = TODO(),
        actionItems = TODO(),
        latestActivity = TODO(),
        lastLoggedIn = TODO(),
        lastLoggedOut = TODO()
    )*/

    /* [Commented First] Because of conflicting TaskID datatype between model and DB*/
    /*private val _thisUser2 = MutableStateFlow<User?>(null)*/
    /*val user2: StateFlow<User?> get() = _thisUser2*/

    private val _thisUser2 = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _thisUser2

    /* Set currently authed user data into this session */
    /*fun setThisUser(thisUserData: User){*/
    fun setThisUser(thisUserData: User){

        _thisUser2.value = thisUserData

        Log.d("This User", _thisUser2.value.toString())

    }

    /* Get current authed user */
    /*fun getThisUser(): User?{*/
    fun getThisUser(): User?{

        return user.value

    }


    fun getCurrentUserByName(requestingUsername: String): UserToDelete? {

        /* [Auth Simulation] Iterate user list */
        for (user in userList) {

            Log.d("[Auth Sim]", user.toString())

            if (user.username.contains(requestingUsername)) {

                Log.d("[Auth Sim]", "Hit")

                return user;

            }

        }

        return null

    }


}