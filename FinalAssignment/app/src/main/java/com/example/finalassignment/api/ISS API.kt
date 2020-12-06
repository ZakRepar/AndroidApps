package com.example.finalassignment.api

import com.example.finalassignment.address.ISSPassTime
import retrofit2.http.GET
import retrofit2.http.Query

interface ISSAPI {

    //http://api.open-notify.org/iss-pass.json?lat=20&lon=20

    @GET("iss-pass.json")
    suspend fun getAddress(@Query("lat") latitude: Double, @Query("lon") longitude: Double) : ISSPassTime
}