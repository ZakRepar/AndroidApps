package com.example.finalassignment.api

import com.example.finalassignment.address.Address
import retrofit2.http.GET

interface ISSAPI {

    //http://api.open-notify.org/iss-pass.json?lat=20&lon=20

    @GET
    suspend fun getAddress() : List<Address>
}