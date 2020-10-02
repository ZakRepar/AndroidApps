package zak.assignment2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryDataAdapter(private val dataset: List<String>) : RecyclerView.Adapter<HistoryDataAdapter.ViewHolder>() {

    inner class ViewHolder(listViewItem: View): RecyclerView.ViewHolder(listViewItem) {
        val textViewHistory = listViewItem.findViewById<TextView>(R.id.textViewHistory)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewHistory.text = dataset[position]
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryDataAdapter.ViewHolder {
        val historyView = LayoutInflater.from(parent.context).inflate(R.layout.history_layout, parent, false)
        return ViewHolder(historyView)
    }



    override fun getItemCount(): Int {
        return dataset.size
    }


} //end class