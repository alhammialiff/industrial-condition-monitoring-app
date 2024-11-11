package com.example.kotlindevcourse

data class User (
    var username: String,
    /* To add profilePicture key-value pair later */
    var name: String,
    var email: String,
    var role: String,
    var department: String,
    var designation: String,
    var actionItems: ActionItems,
    var latestActivity: String,
    var lastLoggedIn: String,
    var lastLoggedOut: String
)