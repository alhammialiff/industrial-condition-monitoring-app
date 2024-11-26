package com.example.kotlindevcourse

import kotlinx.serialization.Serializable


/* [Field Task Data Model] */
@Serializable
data class FieldTask2 (

    val taskID: String,
    val action: String,
    val taskSteps: Array<TaskStep>,
    val timestamp: String,
    val location: String,
    val priority: String,
    val taskFor: String,
    val completed: Boolean

)