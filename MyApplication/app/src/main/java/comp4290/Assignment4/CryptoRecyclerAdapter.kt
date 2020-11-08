package comp4290.Assignment4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CryptoRecyclerAdapter (private val controller: CryptoController) : RecyclerView.Adapter<CryptoRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val cryptoItemView: View): RecyclerView.ViewHolder(cryptoItemView) {

        val textViewId: TextView = cryptoItemView.findViewById<TextView>(R.id.textViewId)
        val textViewLogo: TextView = cryptoItemView.findViewById<TextView>(R.id.textViewLogo)
        val textViewSymbol: TextView = cryptoItemView.findViewById<TextView>(R.id.textViewSymbol)
        val textViewPrice: TextView = cryptoItemView.findViewById<TextView>(R.id.textViewPrice)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cryptoItemView = LayoutInflater.from(parent.context).inflate(R.layout.crypto_item, parent)
        return ViewHolder(cryptoItemView)
    }



    override fun getItemCount(): Int {
        return controller.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val crypto = controller.cryptoAtIndex(position)

        if (crypto != null) {
            holder.textViewId.text = crypto?.id
            holder.textViewLogo.text = crypto?.logo
            holder.textViewSymbol.text = crypto?.symbol
            holder.textViewPrice.text = crypto?.price.toString()
        }
        else {
            holder.textViewId.text = ""
            holder.textViewLogo.text = ""
            holder.textViewSymbol.text = ""
            holder.textViewPrice.text = ""
        }
    }

} // end class