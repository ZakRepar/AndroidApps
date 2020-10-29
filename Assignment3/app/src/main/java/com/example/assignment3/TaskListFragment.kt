package com.example.assignment3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.model.Task


class TaskListFragment : Fragment() {

    val dataController = TaskEntryController()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataController.add(Task("Assignment 3", null, "high", null))
        dataController.add(Task("Project 2", "\uD83D\uDE00", null, "closed"))
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_task_list, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerViewTaskList)
        recycler.layoutManager = LinearLayoutManager(this.context)
        recycler.adapter = TaskListRecyclerAdapter(dataController.taskEntries)

        return view
    }

} // end class