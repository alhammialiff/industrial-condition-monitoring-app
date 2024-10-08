package com.example.kotlindevcourse

/*
*  [TO COMPARTMENTALISE]
* */
/* Task Class */
data class FieldTask (

    val taskID: Int,
    val action: String,
    val taskSteps: Array<TaskStep>,
    val timestamp: String,
    val location: String,
    val priority: String,
    val taskFor: String,
    val completed: Boolean

)