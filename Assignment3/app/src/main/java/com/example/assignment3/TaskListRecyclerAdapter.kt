package com.example.assignment3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskListRecyclerAdapter (private val dataset: List<Task>) : RecyclerView.Adapter<TaskListRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val listViewItem: View): RecyclerView.ViewHolder(listViewItem) {

        val textViewDescription: TextView = listViewItem.findViewById<TextView>(R.id.textViewDescription)
        val textViewPriority: TextView = listViewItem.findViewById<TextView>(R.id.textViewPriority)
        val textViewEmoji: TextView = listViewItem.findViewById<TextView>(R.id.textViewEmoji)
        val textViewOpened: TextView = listViewItem.findViewById<TextView>(R.id.textViewOpened)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListRecyclerAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tasklist_item, parent, false)
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewDescription.text = dataset[position].description
        holder.textViewPriority.text = dataset[position].priority
        holder.textViewEmoji.text = dataset[position].emoji
        holder.textViewOpened.text = dataset[position].opened.toString()
    }

    override fun getItemCount(): Int {
        return dataset.size
    }


} //end class