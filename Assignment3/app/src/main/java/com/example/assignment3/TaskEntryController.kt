package com.example.assignment3

import com.example.assignment3.model.Task

class TaskEntryController {

    private val tasks = ArrayList<Task>()

    val taskEntries: List<Task> get() { return tasks.toList() }

    fun add(task: Task) {
        if (tasks.find { it.description == task.description } == null ) {
            tasks.add(task)
        }
    }

} // end class