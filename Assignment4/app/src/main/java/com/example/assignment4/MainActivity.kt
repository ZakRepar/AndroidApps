package com.example.assignment4

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainActivity : AppCompatActivity() {


    var recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView?.layoutManager = LinearLayoutManager(this)

        if (isNetworkConnected()) {

                doAsync {
                    val endPoint = "https://api.coingecko.com/api/v3/coins"
                    val jsonString = URL(endPoint).readText()

                    Log.d("Crypto", jsonString)

                    val type = object: TypeToken<ArrayList<Crypto>>() {}.type
                    val cryptoList = Gson().fromJson<ArrayList<Crypto>>(jsonString, type)

                    Log.d("Crypto", "$cryptoList")

                    uiThread { recyclerView?.adapter = CryptoRecyclerAdapter(CryptoController(cryptoList)) }
                }
        }
        else {
            AlertDialog.Builder(this).setTitle("Error...")
                    .setMessage("No internet access!")
                    .setPositiveButton(android.R.string.ok) { _, _ -> }
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
        }
    }



    private fun isNetworkConnected(): Boolean {

        val connectivityManager =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetworkCapabilities = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        return when {
            activeNetworkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetworkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetworkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

} // end class