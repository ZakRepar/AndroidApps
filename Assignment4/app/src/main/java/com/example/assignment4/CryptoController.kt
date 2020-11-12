package com.example.assignment4

class CryptoController (var cryptos: ArrayList<Crypto>){

    //var cryptos = mutableListOf<Crypto>()

    val size: Int get() { return cryptos.size }



    fun cryptoAtIndex(index: Int) : Crypto? {

        if ((index >= 0) && (index <= cryptos.size)) {
            return cryptos[index]
        }

        return null
    }



    fun add(crypto: Crypto) {

        if (cryptos.find { it.id == crypto.id } == null) {
            cryptos.add(crypto)
        }
    }

} // end class