package com.example.finalassignment.api

import com.example.finalassignment.address.Address
import retrofit2.http.GET
import retrofit2.http.Query

interface ISSAPI {

    //http://api.open-notify.org/iss-pass.json?lat=20&lon=20

    @GET("address")
    suspend fun getAddress(@Query("latitude") latitude:String? = null, @Query("longitude") longitude:String? = null) : List<Address>
}