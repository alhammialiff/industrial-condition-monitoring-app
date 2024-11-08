package com.example.kotlindevcourse.states

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlindevcourse.ActionItems
import com.example.kotlindevcourse.FieldTask
import com.example.kotlindevcourse.Screen
import com.example.kotlindevcourse.TaskStep
import com.example.kotlindevcourse.User

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
        User(
            username = "stephanbodzin",
            name = "Stephan Bodzin",
            department = "Electrical",
            designation = "Electrician",
            actionItems = ActionItems(
                outstanding = mutableListOf(
                    FieldTask(
                        0,
                        "Lube Oil Change",
                        arrayOf(
                            TaskStep(
                                0,
                                "Unscrew Oiler Cap from Oiler"
                            ),
                            TaskStep(
                                1,
                                "Refill Lube Oil till the Max line"
                            ),
                            TaskStep(
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
                    FieldTask(
                        1,
                        "Check Pump Flow Rate",
                        arrayOf(
                            TaskStep(
                                0,
                                "Unscrew Oiler Cap from Oiler"
                            ),
                            TaskStep(
                                1,
                                "Refill Lube Oil till the Max line"
                            ),
                            TaskStep(
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
                    FieldTask(
                        2,
                        "Examine Furnace",
                        arrayOf(
                            TaskStep(
                                0,
                                "Unscrew Oiler Cap from Oiler"
                            ),
                            TaskStep(
                                1,
                                "Refill Lube Oil till the Max line"
                            ),
                            TaskStep(
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
        User(
            username = "tadaoando",
            name = "Tadao Ando",
            department = "Facilities Management",
            designation = "Field Operator",
            actionItems = ActionItems(
                outstanding = mutableListOf(
                    FieldTask(
                        4,
                        "Examine Fire Extinguisher Rack",
                        arrayOf(
                            TaskStep(
                                0,
                                "Unscrew Oiler Cap from Oiler"
                            ),
                            TaskStep(
                                1,
                                "Refill Lube Oil till the Max line"
                            ),
                            TaskStep(
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
                    FieldTask(
                        2,
                        "Check Smart Pump Meter Reader",
                        arrayOf(
                            TaskStep(
                                0,
                                "Unscrew Oiler Cap from Oiler"
                            ),
                            TaskStep(
                                1,
                                "Refill Lube Oil till the Max line"
                            ),
                            TaskStep(
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
                    FieldTask(
                        2,
                        "Examine Furnace",
                        arrayOf(
                            TaskStep(
                                0,
                                "Unscrew Oiler Cap from Oiler"
                            ),
                            TaskStep(
                                1,
                                "Refill Lube Oil till the Max line"
                            ),
                            TaskStep(
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
        User(
            username = "johnroti",
            name = "John Roti",
            department = "Process Operations",
            designation = "Process Technician",
            actionItems = ActionItems(
                outstanding = mutableListOf(
                    FieldTask(
                        2,
                        "Examine Pump Electrical DB",
                        arrayOf(
                            TaskStep(
                                0,
                                "Unscrew Oiler Cap from Oiler"
                            ),
                            TaskStep(
                                1,
                                "Refill Lube Oil till the Max line"
                            ),
                            TaskStep(
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
                    FieldTask(
                        2,
                        "Examine Machine Line Current Stability",
                        arrayOf(
                            TaskStep(
                                0,
                                "Unscrew Oiler Cap from Oiler"
                            ),
                            TaskStep(
                                1,
                                "Refill Lube Oil till the Max line"
                            ),
                            TaskStep(
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
                    FieldTask(
                        2,
                        "Examine Machine Line Current Stability",
                        arrayOf(
                            TaskStep(
                                0,
                                "Unscrew Oiler Cap from Oiler"
                            ),
                            TaskStep(
                                1,
                                "Refill Lube Oil till the Max line"
                            ),
                            TaskStep(
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

    private val _currentUser: MutableLiveData<User> = MutableLiveData<User>()

    fun getCurrentUserByName(requestingUsername: String): User? {

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

    fun addNewUser(newUser: User) {

        userList.add(newUser)
        Log.d("[Add New User", userList.toString())

    }

    fun setCurrentUser(currentUser: User): Unit {

        _currentUser.value = currentUser

    }

}