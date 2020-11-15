package com.example.cryptoassignment.model


class Coin (val image: CoinImages?, val id: String, val symbol: String?, val price: MarketData?)

class CoinImages(val thumb: String?, val small: String?, val large: String){
}

class MarketData(val prices: List<CurrentPrice>?)

class CurrentPrice(val origin: String?, val price: Double?)