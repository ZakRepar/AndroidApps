package com.example.cryptoassignment.model


class Coin (val image: CoinImages?, val id: String, val symbol: String?, val usd: Double?)

class CoinImages(val thumb: String?, val small: String?, val large: String)