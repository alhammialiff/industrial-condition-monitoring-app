package com.example.kotlindevcourse

data class LoginResponse(

    var responseCode: Int,
    /* [Commented First] Because of conflicting TaskID datatype between model and DB*/
    /*var data: User?,*/
    var data: User2?,
    var accessToken: String

)