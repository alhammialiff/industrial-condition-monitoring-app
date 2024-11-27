package com.example.kotlindevcourse

import kotlinx.serialization.Serializable


@Serializable
data class UserToDelete (
    var id: String,
    var username: String,
    /* To add profilePicture key-value pair later */
    var name: String,
    var email: String,
    var role: String,
    var department: String,
    var designation: String,
    var actionItemsToDelete: ActionItemsToDelete?,
    var latestActivity: String,
    var lastLoggedIn: String,
    var lastLoggedOut: String
)