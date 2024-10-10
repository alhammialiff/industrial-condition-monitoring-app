package com.example.kotlindevcourse

import android.util.Log
import androidx.lifecycle.LiveData

/* [TaskList Data Model]
*  Allows accessing the mutable list like a normal array
*  (To confirm again)
*  */
open class TaskList {

    var tasklist: MutableList<FieldTask>
    var taskIndex: Int = 0

    constructor(fieldTaskList: MutableList<FieldTask>) {

        this.tasklist = fieldTaskList

    }

    /* TODO - Get task list */
    /* Get all task */
    fun getAllTasks(): MutableList<FieldTask>{

//        Log.d("Task List getTask()", "Value - " + this.tasklist);

        /* Add task */
        return this.tasklist

    }

    /* Get a specific task by its index */
    fun getTaskByIndex(index: Int): FieldTask{

        Log.d("5. Task List getTaskByIndex()", "Value - " + this.tasklist[index]);

        return this.tasklist[index]

    }

    /* TODO - Set Task */
    fun setTask(fieldTask: FieldTask){

        /* Add task */
        this.tasklist.add(taskIndex, fieldTask)

        /* Increment index after add */
        this.taskIndex++

    }

    /* TODO - Remove task */

    /* TODO - Update task */

}