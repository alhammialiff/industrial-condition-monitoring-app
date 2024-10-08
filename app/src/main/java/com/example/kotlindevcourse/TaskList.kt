package com.example.kotlindevcourse

import android.util.Log

/* TaskList Class */
open class TaskList {

    var tasklist: MutableList<FieldTask>
    var taskIndex: Int = 0

    constructor(fieldTaskList: MutableList<FieldTask>) {

        this.tasklist = fieldTaskList

    }

    /* TODO - Get task list */
    /* Get Task */
    fun getTask(): MutableList<FieldTask>{

        Log.d("Task List getTask()", "Value - " + this.tasklist);

        /* Add task */
        return this.tasklist

    }

    /* Set Task */
    fun setTask(fieldTask: FieldTask){

        /* Add task */
        this.tasklist.add(taskIndex, fieldTask)

        /* Increment index after add */
        this.taskIndex++

    }

    /* TODO - Remove task */

    /* TODO - Update task */

}