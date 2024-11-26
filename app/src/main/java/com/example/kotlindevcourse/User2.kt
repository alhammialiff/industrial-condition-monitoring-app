package com.example.kotlindevcourse

import kotlinx.serialization.Serializable

@Serializable
data class User2 (
    var id: String,
    var username: String,
    /* To add profilePicture key-value pair later */
    var name: String,
    var email: String,
    var role: String,
    var department: String,
    var designation: String,
    var actionItems: ActionItems2?,
    var latestActivity: String,
    var lastLoggedIn: String,
    var lastLoggedOut: String
)