package com.example.cryptoassignment.model


class Coin (val image: CoinImages?, val id: String, val symbol: String?, val market_data: PriceList?)

class CoinImages(val thumb: String?, val small: String?, val large: String){
}

class PriceList(val current_price: List<CurrentPrice>?)

class CurrentPrice(val usd: String?, val price: Double?)