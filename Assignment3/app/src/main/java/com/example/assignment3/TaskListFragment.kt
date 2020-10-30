package com.example.assignment3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment3.model.Task


class TaskListFragment : Fragment(), TaskListRecyclerAdapter.OnItemClickListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TaskEntryController.add(Task("Assignment 3", null, "high", null))
        TaskEntryController.add(Task("Project 2", "\uD83D\uDE00", null, "opened"))
    }



    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_task_list, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerViewTaskList)
        recycler.layoutManager = LinearLayoutManager(this.context)
        recycler.adapter = TaskListRecyclerAdapter(TaskEntryController.taskEntries, this)

        return view
    }



    override fun onItemClick(position: Int) {

        val argument = Bundle()
        argument.putInt(ARG_ENTRY_INDEX, position)
        findNavController().navigate(R.id.action_taskListFragment_to_taskCreateFragment, argument)
    }

} // end class