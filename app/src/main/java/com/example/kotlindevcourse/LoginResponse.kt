package com.example.kotlindevcourse

data class LoginResponse(

    var responseCode: Int,
    var data: Any,
    var accessToken: String

)