package com.example.kotlindevcourse

import kotlinx.serialization.Serializable

@Serializable
data class User (
    var id: String,
    var username: String,
    /* To add profilePicture key-value pair later */
    var name: String,
    var email: String,
    var role: String,
    var department: String,
    var designation: String,
    var allTasks: AllTasks?,
    var latestActivity: String,
    var lastLoggedIn: String,
    var lastLoggedOut: String
)