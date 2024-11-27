package com.example.kotlindevcourse

import kotlinx.serialization.*

@Serializable
data class AllTasks (
    var outstanding: MutableList<Task>,
    var ongoing: MutableList<Task>,
    var completed: MutableList<Task>
)