package com.example.kotlindevcourse.states

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlindevcourse.FieldTaskToDelete
import com.example.kotlindevcourse.TaskStepToDelete
import com.example.kotlindevcourse.TaskList

/*
* [ViewModel] To Hoist tasks data up so that
*             screens can access it while retaining
*             statelessness
* */
class TasksViewModel: ViewModel(){

    /* [DUMMY TASK DATA]
    *  Data is prepped for consumption over here via
    *  mutableListOf.
    *
    *  Data takes the form of TaskList class
    *  */
    var fieldTaskToDeleteList = TaskList(

        /* Data is prepared here */
        mutableListOf(

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
            ),
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
            ),
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
                false
            )

        )

    )

    // Mutable Live Data for Current Task Index
    //    private var _currentTaskIndex: MutableLiveData<Int> = MutableLiveData(0)
    private val _currentTaskIndex: MutableLiveData<Int> = MutableLiveData<Int>()

    // Accessible data to be passed over as index to access TaskList
    private val currentTaskIndex: Int? = _currentTaskIndex.value

    var taskMasterList: TaskList = fieldTaskToDeleteList

    /* State handler to send initial data down to composable */
    fun getInitTaskList(): TaskList {

        return taskMasterList

    }

    /* State handler to get current task ID, which is the index in tasklist */
    private fun getCurrentTaskIndex(): Int{

        return currentTaskIndex!!

    }

    fun setCurrentTaskIndex(taskIndex: Int): Unit{

        Log.d("2. Passing Curr Task Idx", "Value - " + taskIndex);

        _currentTaskIndex.value = taskIndex

        Log.d("3. After Set Curr Task Idx", "Value - " + _currentTaskIndex.value);

    }


    /* State handler to retrieve specific task by index of Task Card (i.e currentTaskIndex) */
    fun getTaskByCurrentIndex(TASK_ID: Int): FieldTaskToDelete{

        var specificFieldTask = taskMasterList.getTaskByIndex(TASK_ID)

        return specificFieldTask

    }


}