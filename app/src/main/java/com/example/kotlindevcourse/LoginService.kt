package com.example.kotlindevcourse

import android.util.Log
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("login")
    fun login(
        @Body data: AuthenticatingUser
    ): Call<LoginResponse?>?

}