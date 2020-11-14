package com.example.cryptoassignment.model

class Coin (val logo: Image?, val id: String, val symbol: String?, val price: MarketData?)

class Image(val size: List<String>?) {
    operator fun get(i: Int): String? {
        return size?.get(i)
    }
}

class MarketData(val prices: List<CurrentPrice>?)

class CurrentPrice(val origin: String?, val price: Double?)