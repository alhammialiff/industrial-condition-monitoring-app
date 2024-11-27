package com.example.kotlindevcourse

import kotlinx.serialization.Serializable


/* [Field Task Data Model] */
@Serializable
data class FieldTaskToDelete (

    val taskID: Int,
    val action: String,
    val taskStepToDeletes: Array<TaskStepToDelete>,
    val timestamp: String,
    val location: String,
    val priority: String,
    val taskFor: String,
    val completed: Boolean

)