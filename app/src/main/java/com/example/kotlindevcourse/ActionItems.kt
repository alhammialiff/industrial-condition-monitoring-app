package com.example.kotlindevcourse

import kotlinx.serialization.*

@Serializable
data class ActionItems (
    var outstanding: MutableList<FieldTask>,
    var ongoing: MutableList<FieldTask>,
    var completed: MutableList<FieldTask>
)