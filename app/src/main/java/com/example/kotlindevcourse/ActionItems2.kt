package com.example.kotlindevcourse

import kotlinx.serialization.*

@Serializable
data class ActionItems2 (
    var outstanding: MutableList<FieldTask2>,
    var ongoing: MutableList<FieldTask2>,
    var completed: MutableList<FieldTask2>
)