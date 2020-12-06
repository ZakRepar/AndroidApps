package com.example.finalassignment.api

import com.example.finalassignment.address.ISSPassTime
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddressDataAdapter {

    private val service: ISSAPI

    companion object {
        const val BASE_URL = "http://api.open-notify.org/"
    }

    init {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(ISSAPI::class.java)
    }



    suspend fun getISSAPI(latitude: Double, longitude: Double) : ISSPassTime {
        return service.getAddress(latitude, longitude)
    }

} //end class