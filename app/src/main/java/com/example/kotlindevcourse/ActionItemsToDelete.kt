package com.example.kotlindevcourse

import kotlinx.serialization.*

@Serializable
data class ActionItemsToDelete (
    var outstanding: MutableList<FieldTaskToDelete>,
    var ongoing: MutableList<FieldTaskToDelete>,
    var completed: MutableList<FieldTaskToDelete>
)