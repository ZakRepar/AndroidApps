package com.example.assignment3

import com.example.assignment3.model.Task
import java.security.acl.AclEntry

object TaskEntryController {

    private val tasks = ArrayList<Task>()

    val taskEntries: List<Task> get() { return tasks.toList() }

    fun add(task: Task) {
        if (tasks.find { it.description == task.description } == null ) {
            tasks.add(task)
        }
    }



    fun update(task: Task, position: Int) {
        if ((position >= 0) && (position <= tasks.size)) {
            tasks[position] = task
        }
    }

} // end class