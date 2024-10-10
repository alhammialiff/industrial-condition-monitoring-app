package com.example.kotlindevcourse

/* [Field Task Data Model] */
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