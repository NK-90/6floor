package com.example.a6floor

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MotorControlService {
    @GET("control/{direction}")
    fun controlMotor(@Path("direction") direction: String): Call<MotorStatus>
}