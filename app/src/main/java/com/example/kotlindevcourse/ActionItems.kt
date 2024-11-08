package com.example.kotlindevcourse

data class ActionItems (
    var outstanding: MutableList<FieldTask>,
    var ongoing: MutableList<FieldTask>,
    var completed: MutableList<FieldTask>
)