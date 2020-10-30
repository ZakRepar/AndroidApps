package com.example.assignment3

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.model.Task




class TaskListRecyclerAdapter (private val dataset: List<Task>, private val onItemCLickListener: OnItemClickListener) : RecyclerView.Adapter<TaskListRecyclerAdapter.ViewHolder>() {



    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }



    inner class ViewHolder(private val listViewItem: View, private val onItemCLickListener: OnItemClickListener): RecyclerView.ViewHolder(listViewItem) {

        val textViewDescription: TextView = listViewItem.findViewById<TextView>(R.id.textViewDescription)
        val textViewPriority: TextView = listViewItem.findViewById<TextView>(R.id.textViewPriority)
        val textViewEmoji: TextView = listViewItem.findViewById<TextView>(R.id.textViewEmoji)
        val textViewOpened: TextView = listViewItem.findViewById<TextView>(R.id.textViewState)

        init {
            listViewItem.setOnClickListener {
                Log.d("Task", "clicked item $adapterPosition")
                onItemCLickListener.onItemClick(adapterPosition)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListRecyclerAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tasklist_item, parent, false)
        return ViewHolder(itemView, onItemCLickListener)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textViewDescription.text = dataset[position].description

        if (dataset[position].priority != null) {
            holder.textViewPriority.text = dataset[position].priority
        }
        else {
            holder.textViewPriority.text = ""
        }

        if (dataset[position].emoji != null) {
            holder.textViewEmoji.text = dataset[position].emoji
        }
        else {
            holder.textViewEmoji.text = ""
        }

        if (dataset[position].state != null) {
            holder.textViewOpened.text = dataset[position].state
        }
        else {
            holder.textViewOpened.text = ""
        }
    }



    override fun getItemCount(): Int {
        return dataset.size
    }


} //end class