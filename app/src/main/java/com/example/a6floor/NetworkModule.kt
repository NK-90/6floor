package com.example.a6floor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private const val BASE_URL = "http://192.168.0.128:5000/"// 라즈베리 파이의 실제 IP 주소로 변경

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val motorControlService: MotorControlService = retrofit.create(MotorControlService::class.java)
}