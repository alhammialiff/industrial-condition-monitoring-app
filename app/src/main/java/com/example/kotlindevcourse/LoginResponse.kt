package com.example.kotlindevcourse

data class LoginResponse(

    var responseCode: Int,
    var data: User?,
    var accessToken: String

)