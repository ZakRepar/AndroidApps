package com.example.cryptoassignment.model

import com.example.cryptoassignment.api.CoinDataAdapter
import kotlinx.coroutines.*

object CoinController {

    var coins = listOf<Coin>()

    val size: Int get() { return coins.size }



    fun getCoinAtPosition(position: Int) : Coin? {

        if ((position >= 0 ) && (position < coins.size)) {
            return coins[position]
        }

        return null
    }



    fun refresh(errorHandler: CoroutineExceptionHandler, completion : () -> Unit) {
        val mainActivityJob = Job()
        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)

        coroutineScope.launch(errorHandler) {

            coins = CoinDataAdapter().getCoins()

            completion()
        }
    }

} // end class