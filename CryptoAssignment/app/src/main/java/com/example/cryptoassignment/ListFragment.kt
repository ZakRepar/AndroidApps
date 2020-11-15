package com.example.cryptoassignment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoassignment.model.CoinController
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineExceptionHandler

const val ARG_ENTRY_INDEX = "entryIndex"

class ListFragment : Fragment(), CoinRecyclerAdapter.CoinRecyclerAdapterDelegate {

    private var entryIndex: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_list, container, false)

        if(entryIndex != null) {
            val coin = CoinController.getCoinAtPosition(entryIndex!!)

            view.findViewById<TextView>(R.id.textViewID).text = coin?.id.orEmpty()
            view.findViewById<TextView>(R.id.textViewSymbol).text = coin?.symbol.orEmpty()
            view.findViewById<TextView>(R.id.textViewPrice).text = coin?.price.toString()

            Picasso.get().load(coin?.image?.thumb).placeholder(R.drawable.coin_default_detail_image).into(view.findViewById<ImageView>(R.id.imageViewLogo))
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateUI()
    }


    fun updateUI() {

        val errorHandler = createErrorHandler()

        if (errorHandler != null) {

            CoinController.refresh(errorHandler) {

                val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)

                recyclerView?.layoutManager = LinearLayoutManager(view?.context)
                recyclerView?.adapter = CoinRecyclerAdapter(CoinController, this)
            }
        }

    }



    private fun createErrorHandler(): CoroutineExceptionHandler? {

        val act = activity
        if (act != null) {

            return CoroutineExceptionHandler { _, exception ->
                AlertDialog.Builder(act).setTitle("Error...")
                    .setMessage(exception.message)
                    .setPositiveButton(android.R.string.ok) { _, _ -> }
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
        }

        return null
    }



    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }

} // end class