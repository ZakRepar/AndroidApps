package com.example.cryptoassignment.api

import com.example.cryptoassignment.model.Coin
import retrofit2.http.GET

interface CoinAPI {

    //https://api.coingecko.com/api/v3/coins


    @GET("coins")
    suspend fun getCoins() : List<Coin>

} // end interface