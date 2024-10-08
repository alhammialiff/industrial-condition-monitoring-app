package com.example.kotlindevcourse.states

import androidx.lifecycle.ViewModel
import com.example.kotlindevcourse.FieldTask
import com.example.kotlindevcourse.TaskStep
import com.example.kotlindevcourse.TaskList
class TasksViewModel: ViewModel(){

    /* [TASK DATA]
    *  Data is prepped for consumption over here via
    *  mutableListOf.
    *
    *  Data takes the form of TaskList class
    *  */
    var fieldTaskList = TaskList(

        /* Data is prepared here */
        mutableListOf(

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
            ),
            FieldTask(
                1,
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
                "Area B-1",
                "low",
                "Area B-4",
                false
            ),
            FieldTask(
                2,
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
                "Area A-1",
                "high",
                "Area B-2",
                false
            )

        )
//
    )

    //    private var _taskMasterList: MutableState<TaskList> = MutableState()
    var taskMasterList: TaskList = fieldTaskList

    /* State handler to send initial data down to composable */
    fun getInitTaskList(): TaskList {

        return taskMasterList

    }

}