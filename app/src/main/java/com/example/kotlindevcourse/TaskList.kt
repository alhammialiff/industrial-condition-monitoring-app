package com.example.kotlindevcourse

import android.util.Log

/* [TaskList Data Model]
*  Allows accessing the mutable list like a normal array
*  (To confirm again)
*  */
open class TaskList {

    var tasklist: MutableList<FieldTaskToDelete>
    var taskIndex: Int = 0

    constructor(fieldTaskToDeleteList: MutableList<FieldTaskToDelete>) {

        this.tasklist = fieldTaskToDeleteList

    }

    /* TODO - Get task list */
    /* Get all task */
    fun getAllTasks(): MutableList<FieldTaskToDelete>{

//        Log.d("Task List getTask()", "Value - " + this.tasklist);

        /* Add task */
        return this.tasklist

    }

    /* Get a specific task by its index */
    fun getTaskByIndex(index: Int): FieldTaskToDelete{

        Log.d("5. Task List getTaskByIndex()", "Value - " + this.tasklist[index]);

        return this.tasklist[index]

    }

    /* TODO - Set Task */
    fun setTask(fieldTaskToDelete: FieldTaskToDelete){

        /* Add task */
        this.tasklist.add(taskIndex, fieldTaskToDelete)

        /* Increment index after add */
        this.taskIndex++

    }

    /* TODO - Remove task */

    /* TODO - Update task */

}