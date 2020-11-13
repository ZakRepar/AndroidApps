package com.example.cryptoassignment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cryptoassignment.model.CoinController
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateUI()
    }


    fun updateUI() {

        val errorHandler = createErrorHandler()

        if (errorHandler != null) {

            CoinController.refresh(errorHandler) {

                Log.d("Coin", "==== Got Data ====")

                Log.d("Coin", "${CoinController.coins}")
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

} // end class