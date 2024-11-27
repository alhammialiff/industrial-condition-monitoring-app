package com.example.kotlindevcourse

import kotlinx.serialization.Serializable

@Serializable
data class TaskStep2 (
    val stepNum: Int,
    val description: String
    /* TODO: Add more data to pass to route args */
    /* TODO: Image key */
    /* TODO: isFinalStep Flag */
)