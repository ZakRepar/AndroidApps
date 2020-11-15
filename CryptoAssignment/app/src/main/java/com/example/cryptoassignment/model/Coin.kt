package com.example.cryptoassignment.model


class Coin (val image: CoinImages?, val id: String, val symbol: String?, val market_data: MarketData?)

class CoinImages(val thumb: String?, val small: String?, val large: String)

class MarketData(private val current_price: CurrentPrice) {
    fun getPrice(): Double {
        return current_price.usd
    }
}

class CurrentPrice(val usd: Double)