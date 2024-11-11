package com.example.kotlindevcourse

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.*

object RetrofitInstance {

    private const val BASE_URL = "http://10.0.2.2:3005"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val loginService: LoginService by lazy {

        retrofit.create(LoginService::class.java)

    }

}