package com.example.cryptoassignment.api

import com.example.cryptoassignment.model.Coin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoinDataAdapter {

    val service: CoinAPI

    companion object{
        const val BASE_URL = "https://api.coingecko.com/api/v3/"
    }



    init {

        //val logging = HttpLoggingInterceptor()
        //logging.level = HttpLoggingInterceptor.Level.BASIC

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(CoinAPI::class.java)
    }



    suspend fun getCoins() : List<Coin> {
        return service.getCoins()
    }

} // end class