package com.example.assignment3

class TaskEntryController {

    private val tasks = ArrayList<Task>()

    val taskEntries: List<Task> get() { return tasks.toList() }

    fun add(task: Task) {
        if (tasks.find { it.description == task.description } == null ) {
            tasks.add(task)
        }
    }

} // end class