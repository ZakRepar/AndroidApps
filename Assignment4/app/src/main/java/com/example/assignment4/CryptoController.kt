package com.example.assignment4

class CryptoController {

    var cryptoList : MutableList<Crypto> = mutableListOf<Crypto>()

    val size: Int get() { return cryptoList.size }



    fun cryptoAtIndex(index: Int) : Crypto? {

        if ((index >= 0) && (index <= cryptoList.size)) {
            return cryptoList[index]
        }

        return null
    }



    fun add(crypto: Crypto) {

        if (cryptoList.find { it.id == crypto.id } == null) {
            cryptoList.add(crypto)
        }
    }

} // end class